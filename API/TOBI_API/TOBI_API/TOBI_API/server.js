// đăng ký, đăng nhập, thêm giỏ hàng, thay đổi số lượng trong giỏ hàng, thanh toán,
// lấy danh sách sản phẩm, lấy danh sách sản phẩm theo loại
const fs = require('fs')
const bodyParser = require('body-parser')
const jsonServer = require('json-server')
const jwt = require('jsonwebtoken')

const server = jsonServer.create()

// const router = jsonServer.router('./db.json')
// server.use(router)
// không cần thiết vì cần tạo API lọc sản phẩm theo Type

const db = JSON.parse(fs.readFileSync('./db.json', 'UTF-8'))

const middlewares = jsonServer.defaults();
const PORT = process.env.PORT || 3000;

server.use(middlewares);

server.use(jsonServer.defaults());
server.use(bodyParser.urlencoded({extended: true}))
server.use(bodyParser.json())

const SECRET_KEY = '123456789'
const expiresIn = '1h'

function createToken(payload) {
    return jwt.sign(
        payload, 
        SECRET_KEY, 
        {expiresIn})
}

function verifyToken(token) {
    return jwt.verify(
        token, 
        SECRET_KEY,  
        (err, decode) => decode !== undefined ?  decode : err)
}

function isAuthenticated({email, password}){
    return db.users.findIndex(user => user.email === email && user.password === password) !== -1
}

function issetProduct(o1, o2) {
  if (typeof o1 === 'object' && Object.keys(o1).length > 0) {
   return  Object.keys(o1).length === Object.keys(o2).length && Object.keys(o1).every(p => p!=="amount"?issetProduct(o1[p], o2[p]):true)
  } else { return o1 === o2 };
}

function getProduct(id, size, amount) {
  let products = JSON.parse(JSON.stringify(db.products)).filter(product => product.id === id);
  if (products.length <= 0) {
    return "error product";
  }
  let product = products[0];
  let variants = product.variants.filter(variant => variant.size === size);
  console.log(variants);
  if (variants.length <= 0) {
    return "error size";
  }
  product.variants = variants[0];
  product.amount = amount;
  return product;
}
//view all products
server.get('/products/list', function (req, res) {
  res.status(200).json({
    "products" : db.products
  })
})

//view products by type
server.get('/products/list/:type', function (req, res) {
  const type = req.params.type
  const list = db.products.filter(product => product.type === type)
  if (list.length > 0) {
    res.status(200).json({
      "products" : list
    })
  } else {
    res.status(404).json({
      message: "Type of product not found",
    })
  }
})

//view product by id
server.get('/products/get/:id', function (req, res) {
  const id = req.params.id
  const list = db.products.filter(product => product.id == id)
  if (list.length > 0) {
    res.status(200).json({
      "product" : list[0]
    })
  } else {
    res.status(404).json({
      message: "Product not found",
    })
  }
})

//register
server.post('/register', (req, res) => {
    const {fname, lname, email, password} = req.body;
  
    exist_user = db.users.findIndex(x => x.email === email)
    if(exist_user !== -1) {
      return res.status(401).json({
        status: 401,
        message: "Email already in use!",
      })
    }
  
    const new_user = {
      'id': db.users.length>0?db.users[db.users.length-1].id+1:1,
      fname,
      lname,
      email,
      password
    }
  
    db.users.push(new_user);
    fs.writeFileSync('./db.json', JSON.stringify(db), () => {
      if (err) return console.log(err);
      console.log('writing to ' + fileName);
    })
    res.status(201).json({
      status: 201,
      message: "Success",
      data: new_user
    })
})

//login
server.post('/login', (req, res) => {
    const email = req.body.email
    const password = req.body.password

    if (isAuthenticated({email, password}) === false) {
      const status = 401
      const message = 'Incorrect email or password'
      res.status(status).json({status, message})
      return
    }
    const access_token = createToken({email, password})
    res.status(200).json({
      status: 200,
      message: "Success",
      data: {
        access_token
      }
    })
})

//authorize
server.use('/auth',(req, res, next) => {
  if (req.headers.authorization == undefined || req.headers.authorization.split(' ')[0] !== 'Bearer') {
    const status = 401
    const message = 'Bad authorization header'
    res.status(status).json({status, message})
    return
  }
  try {
    let verifyTokenResult;
     verifyTokenResult = verifyToken(req.headers.authorization.split(' ')[1]);

     if (verifyTokenResult instanceof Error) {
       const status = 401
       const message = 'Error: access_token is not valid'
       res.status(status).json({status, message})
       return
     }
     next()
  } catch (err) {
    const status = 401
    const message = 'Token đã hết hạn'
    res.status(status).json({status, message})
  }
})

//view all users
server.get('/auth/users', (req, res) => {
    res.status(200).json({
      status: 200,
      data: {
        "users" : db.users
      }
    })
})

//Xem thông tin user theo id
server.get('/auth/users/:id', ((req, res)=> {
    //let userdb = JSON.parse(fs.readFileSync('./database.json', 'UTF-8'));
      const id = req.params.id;
   
      const exist_id = db.users.findIndex(user =>  user.id == id)
      const user = db.users.filter(user =>  user.id == id)
      if (exist_id !== -1)
      {
          const status = 200
          return res.status(status).json({status, user})
      } else {
      return res.status(401).json({
        status: 401,
        message: "User is not found!!",
      })
}}))

//update user by id
server.patch('/auth/users/:id',(req, res) => {
  const id = req.params.id
  const fname = req.body.fname
  const lname = req.body.lname
  const email = req.body.email
  const password = req.body.password
  const new_pass = req.body.new_pass
  
  const exist_id = db.users.findIndex(user =>  user.id == id)
  if(exist_id !== -1) {
    if (db.users[exist_id].password === password && db.users[exist_id].email === email) {
      db.users[exist_id].fname = fname
      db.users[exist_id].lname = lname
      db.users[exist_id].password = new_pass
      fs.writeFileSync('./db.json', JSON.stringify(db), () => {
        if (err) return console.log(err);
        console.log('writing to ' + fileName);
      })
      return res.status(201).json({
        status: 201,
        message: "Success",
        data: {
          "new_update_user": db.users[exist_id]
        }
      })
    } else {
      return res.status(401).json({
        status: 401,
        message: "Wrong password or email",
      })
    }
  } else {
    return res.status(401).json({
      status: 401,
      message: "User is not found!!",
    })
  }
})

//delete user by id
server.delete('/auth/users/:id', (req, res) => {
    const id = req.params.id
  
    const exist_id = db.users.findIndex(user =>  user.id == id)
    if(exist_id !== -1) {
      db.users.splice(exist_id, 1);
  
      fs.writeFileSync('./db.json', JSON.stringify(db), () => {
        if (err) return console.log(err);
        console.log('writing to ' + fileName);
      })
      return res.status(204).json({
        status: 204,
        message: "Success",
      })
    } else {
      return res.status(401).json({
        status: 401,
        message: "User is not found!!",
      })
    }
})

//view cart
server.get('/cart', (req, res) => {
  res.status(200).json({
    "cart" : db.cart
  })
})

//add to cart
server.post('/cart', (req, res) => {
  const id = req.body.id
  const size = req.body.size
  const amount = req.body.amount
  const item = getProduct(id, size, amount)
  if (item!=="error product" && item!=="error size" && amount > 0 && item.variants.available) {
    let isset = false
    db.cart.forEach(element => {
      if (issetProduct(element, item)) {
        element.amount += amount;
        isset = true;
      }
    });
    if (!isset) {
      db.cart.push(item);
    }
    fs.writeFileSync('./db.json', JSON.stringify(db), () => {
      if (err) return console.log(err);
      console.log('writing to ' + fileName);
    })
    return res.status(201).json({
      status: 201,
      message: "Success",
      data: {
        "new_item" : item,
        "cart" : db.cart
      }
    })
  } else if (item === "error product") {
    return res.status(401).json({
      status: 401,
      message: "ID of product not found",
    })
  } else if (item === "error size") {
    return res.status(401).json({
      status: 401,
      message: "Size of product not found",
    })
  }  else if (amount <= 0) {
    return res.status(401).json({
      status: 401,
      message: "Amount cannot be negative",
    })
  } else if (!item.variants.available) {
    return res.status(401).json({
      status: 401,
      message: "Product is out of stock",
    })
  }
})

//update cart
server.patch('/cart', (req, res) => {
  const id = req.body.id
  const size = req.body.size
  const amount = req.body.amount
  const item = getProduct(id, size, amount)
  if (item!=="error product" && item!=="error size" && amount > 0) {
    let isset = false
    db.cart.forEach(element => {
      if (issetProduct(element, item)) {
        element.amount = amount;
        isset = true;
      }
    });
    if (!isset) {
      return res.status(401).json({
        status: 401,
        message: "Product not found in cart",
      })
    }
    fs.writeFileSync('./db.json', JSON.stringify(db), () => {
      if (err) return console.log(err);
      console.log('writing to ' + fileName);
    })
    return res.status(201).json({
      status: 201,
      message: "Success",
      data: {
        "updated_item" : item,
        "cart" : db.cart
      }
    })
  } else if (item === "error product") {
    return res.status(401).json({
      status: 401,
      message: "ID of product not found",
    })
  } else if (item === "error size") {
    return res.status(401).json({
      status: 401,
      message: "Size of product not found",
    })
  }  else if (amount <= 0) {
    return res.status(401).json({
      status: 401,
      message: "Amount cannot be negative",
    })
  }
})

//delete product in cart
server.delete('/cart', (req, res) => {
  const id = req.body.id
  const size = req.body.size
  const exist_cart = db.cart.findIndex(item => item.id == id && item.variants.size == size)
  if (exist_cart !== -1) {
    db.cart.splice(exist_cart, 1);

    fs.writeFileSync('./db.json', JSON.stringify(db), () => {
      if (err) return console.log(err);
      console.log('writing to ' + fileName);
    })

    return res.status(204).json({
      status: 204,
      message: "Success",
      data: {
        "cart": db.cart
      }
    })
  } else {
    return res.status(401).json({
      status: 401,
      message: "Product not found in cart",
    })
  }
})

//view all orders
server.get('/auth/orders', (req, res) => {
  res.status(200).json({
    status: 200,
    message: "Success",
    data: {
      "orders" : db.orders
    }
  })
})

//view order by id
server.get('/auth/orders/:id', (req, res) => {
  const order_id = parseInt(req.params.id)

  const exist_order = db.orders.findIndex(order => order.ID_Order === order_id)
  if(exist_order !== -1) {
    res.status(200).json({
          status: 200,
          data: {
            'order': db.orders[exist_order]
          }
        })
  } else {
    return res.status(401).json({
      status: 401,
      message: "Order not found!!",
    })
  }
})

//add new order for cart
server.post('/auth/orders', (req, res) => {
  const ID_User = req.body.ID_User
  const exist_user = db.users.findIndex(user => user.id == ID_User);
  const exist_cart = db.cart.length
  if (exist_user !== -1 && exist_cart > 0) {
    const order = JSON.parse(JSON.stringify(db.cart))
    let sum = 0;
    order.forEach(element => {
      sum += element.variants.price*element.amount;
    });
    const total_price = sum;
    const new_order = {
      'ID_Order': db.orders.length>0?db.orders[db.orders.length-1].ID_Order+1:1,
      ID_User,
      "Date_Create": new Date().getTime(),
      total_price,
      order
    }
    db.orders.push(new_order);
    db.cart = [];
    fs.writeFileSync('./db.json', JSON.stringify(db), () => {
      if (err) return console.log(err);
      console.log('writing to ' + fileName);
    })
    return res.status(200).json({
      status: 200,
      message: "Success",
      new_order: new_order
    })
  } else if (exist_cart <= 0) {
    return res.status(401).json({
      status: 401,
      message: "Cart is empty",
    })
  } else if (exist_user == -1) {
    return res.status(401).json({
      status: 401,
      message: "User is not found",
    })
  }
})

//add new order for one product
server.post('/auth/orders/:id', (req, res) => {
  const ID_Product = parseInt(req.params.id);
  const ID_User = req.body.ID_User
  const size = req.body.size
  const amount = req.body.amount
  const item = getProduct(ID_Product, size, amount)
  if (item!=="error product" && item!=="error size" && amount > 0 && item.variants.available) {
    const cart = [];
    cart.push(item);

    const exist_user = db.users.findIndex(user => user.id == ID_User);
    const exist_cart = cart.length
    if (exist_user !== -1 && exist_cart > 0) {
      const order = cart;
      let sum = 0;
      order.forEach(element => {
        sum += element.variants.price*element.amount;
      });
      const total_price = sum;
      const new_order = {
        'ID_Order': db.orders.length>0?db.orders[db.orders.length-1].ID_Order+1:1,
        ID_User,
        "Date_Create": new Date().getTime(),
        total_price,
        order
      }
      db.orders.push(new_order);
      db.cart = [];
      fs.writeFileSync('./db.json', JSON.stringify(db), () => {
        if (err) return console.log(err);
        console.log('writing to ' + fileName);
      })
      return res.status(200).json({
        status: 200,
        message: "Success",
        data: new_order
      })
    } else if (exist_user == -1) {
      return res.status(401).json({
        status: 401,
        message: "User is not found",
      })
    } else if (exist_cart <= 0) {
      return res.status(401).json({
        status: 401,
        message: "Cart is empty",
      })
    } 
  } else if (item === "error product") {
    return res.status(401).json({
      status: 401,
      message: "ID of product not found"
    })
  } else if (item === "error size") {
    return res.status(401).json({
      status: 401,
      message: "Size of product not found",
    })
  }  else if (amount <= 0) {
    return res.status(401).json({
      status: 401,
      message: "Amount cannot be negative",
    })
  } else if (!item.variants.available) {
    return res.status(401).json({
      status: 401,
      message: "Product is out of stock",
    })
  }
})

//delete order by id
server.delete('/auth/orders/:id', (req, res) => {
  const order_id = req.params.id

  const exist_order = db.orders.findIndex(order => order.ID_Order == order_id)
  if(exist_order !== -1) {
    db.orders.splice(exist_order, 1);

    fs.writeFileSync('./db.json', JSON.stringify(db), () => {
      if (err) return console.log(err);
      console.log('writing to ' + fileName);
    })

    return res.status(204).json({
      status: 204,
      message: "Success",
      data: {
        "orders": db.orders
      }
    })
  } else {
    return res.status(401).json({
      status: 401,
      message: "Order not found!!",
    })
  }
})

server.listen(PORT, () => {
  console.log('Run Auth API Server')
})