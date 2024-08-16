<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Contact Form - YumCourt</title>
</head>
<body>
<h1>Contact Form</h1>
<form action="/contact" method="post">
    <label for="contactPhone">Phone Number:</label>
    <input type="tel" id="contactPhone" name="phone" required><br><br>

    <label for="contactAddress">Address:</label>
    <input type="text" id="contactAddress" name="address" required><br><br>

    <input type="submit" value="Submit">
</form>
</body>
</html>
