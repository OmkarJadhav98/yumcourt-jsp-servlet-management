<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Order Form - YumCourt</title>
</head>
<body>
<h1>Order Form</h1>
<form action="/order" method="post">
    <label for="orderId">Order ID:</label>
    <input type="text" id="orderId" name="orderId" required><br><br>

    <label for="customerId">Customer ID:</label>
    <input type="text" id="customerId" name="customerId" required><br><br>

    <label for="menuId">Menu ID:</label>
    <input type="text" id="menuId" name="menuId" required><br><br>

    <label for="orderDate">Order Date:</label>
    <input type="date" id="orderDate" name="orderDate" required><br><br>

    <label for="orderStatus">Order Status:</label>
    <select id="orderStatus" name="orderStatus" required>
        <option value="Pending">Pending</option>
        <option value="Processing">Processing</option>
        <option value="Delivered">Delivered</option>
        <option value="Cancelled">Cancelled</option>
    </select><br><br>

    <input type="submit" value="Submit">
</form>
</body>
</html>
