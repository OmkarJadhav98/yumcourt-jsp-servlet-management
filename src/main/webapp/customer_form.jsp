<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Customer Form - YumCourt</title>
</head>
<body>
<h1>Customer Form</h1>
<form action="/customer" method="post">
    <label for="customerName">Customer Name:</label>
    <input type="text" id="customerName" name="name" required><br><br>

    <label for="customerEmail">Email:</label>
    <input type="email" id="customerEmail" name="email" required><br><br>

    <label for="customerPhone">Phone Number:</label>
    <input type="tel" id="customerPhone" name="phone" required><br><br>

    <label for="customerAddress">Address:</label>
    <input type="text" id="customerAddress" name="address" required><br><br>

    <input type="submit" value="Submit">
</form>
</body>
</html>
