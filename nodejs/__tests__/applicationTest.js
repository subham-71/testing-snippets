const request = require("supertest");

app = require("../src/indexTest")

server = app.listen(8000, () => console.log('App is listening on url http://localhost:' + 8000));

const id = "euv3eXvV6kXSFspxH6dgEG6P85g2"

test ("status-changed-pass", async () => {
        const data = {
                appId: id,
        }
        const response = await request(server).post("/application/change-status").send(data)
        expect(response.statusCode).toBe(200)        
})


test ("status-changed-fail", async () => {
        const data = {}
        const response = await request(server).post("/application/change-status").send(data)
        expect(response.statusCode).toBe(400)        
})


server.close()
