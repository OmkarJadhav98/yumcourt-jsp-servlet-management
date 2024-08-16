<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Restaurant Form - YumCourt</title>
</head>
<body>
<h1>Restaurant Form</h1>
<form action="/restaurant" method="post">
    <label for="restaurantName">Restaurant Name:</label>
    <input type="text" id="restaurantName" name="name" required><br><br>

    <label for="restaurantAddress">Address:</label>
    <input type="text" id="restaurantAddress" name="address" required><br><br>

    <label for="restaurantPhone">Phone Number:</label>
    <input type="tel" id="restaurantPhone" name="phone" required><br><br>

    <label for="restaurantEmail">Email:</label>
    <input type="email" id="restaurantEmail" name="email" required><br><br>

    <input type="submit" value="Submit">
</form>
</body>
</html>
