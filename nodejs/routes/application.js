const express = require('express');
const {
       changeStatus
    } = require('../controllers/applicationController');

const router = express.Router();

router.post('/change-status', changeStatus);

module.exports = {
    routes: router
}