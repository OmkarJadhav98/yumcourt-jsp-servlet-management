<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Delivery Executive Form - YumCourt</title>
</head>
<body>
<h1>Delivery Executive Form</h1>
<form action="/delivery-executive" method="post">
    <label for="executiveName">Name:</label>
    <input type="text" id="executiveName" name="name" required><br><br>

    <label for="executivePhone">Phone Number:</label>
    <input type="tel" id="executivePhone" name="phone" required><br><br>

    <label for="executiveEmail">Email:</label>
    <input type="email" id="executiveEmail" name="email" required><br><br>

    <label for="executiveAddress">Address:</label>
    <input type="text" id="executiveAddress" name="address" required><br><br>

    <input type="submit" value="Submit">
</form>
</body>
</html>
