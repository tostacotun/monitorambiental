db.createUser(
    {
        user: "rootuser",
        pwd: "rootpass",
        roles: [
            {
                role: "readWrite",
                db: "monitor"
            }
        ]
    }
);
db.createCollection("test"); //MongoDB creates the database when you first store data in that database
