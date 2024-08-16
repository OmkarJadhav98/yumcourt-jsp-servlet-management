<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Kart Form - YumCourt</title>
</head>
<body>
<h1>Kart Form</h1>
<form action="/kart" method="post">
    <label for="kartId">Kart ID:</label>
    <input type="text" id="kartId" name="kartId" required><br><br>

    <label for="customerId">Customer ID:</label>
    <input type="text" id="customerId" name="customerId" required><br><br>

    <label for="menuId">Menu ID:</label>
    <input type="text" id="menuId" name="menuId" required><br><br>

    <label for="quantity">Quantity:</label>
    <input type="number" id="quantity" name="quantity" required><br><br>

    <input type="submit" value="Submit">
</form>
</body>
</html>
