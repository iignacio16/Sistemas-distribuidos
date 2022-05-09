const app = require('express')()
const http = require('http').Server(app)
const io = require('socket.io')(http)
const port = process.env.PORT || 3000

app.get('/', (req, res) => {
    res.sendFile(__dirname + '/index.html')
})

app.get('/imagen', (req, res)=>{
    res.sendFile(__dirname + '/imgs/defaultimg.jpeg')
})

io.on('connection', (socket) => {
    console.log('new connection', socket.id)

    socket.on('chat message', (msg, img) => {
        //escucha los datos enviados
        io.emit('chat message', msg, img)
    })
})

http.listen(port, () => {
    console.log(`Socket.IO server running at http://localhost:${port}/`)
})
