'use strict';


const changeStatus = async (req, res) => {
    try {

        const appId = req.body.appId;
        console.log(appId)

        if (!appId) {
            throw new Error("appId is required");
        }

        // Change status of the application with the given id
        res.status(200).send("Status Changed");
    }
    catch (error) {
        res.status(400).send(error.message);
    }
}


module.exports = {
    changeStatus
}
