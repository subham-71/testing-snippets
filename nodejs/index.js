//'use strict';
const express = require('express');
const cors = require('cors');
const bodyParser = require('body-parser');


const application = require('./routes/application');

const app = express();
app.use(bodyParser.json());

app.use('/application', application.routes);

app.get('/', (req,res) => {
    res.send('Welcome to the Source M App');
});

app.listen(8000, () => console.log('App is listening on url http://localhost:' + 8000));
module.exports = app;

