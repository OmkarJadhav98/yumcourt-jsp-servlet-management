<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Address Form - YumCourt</title>
</head>
<body>
<h1>Address Form</h1>
<form action="/address" method="post">
    <label for="name">Name:</label>
    <input type="text" id="name" name="name" required><br><br>

    <label for="flatNo">Flat No:</label>
    <input type="text" id="flatNo" name="flatNo" required><br><br>

    <label for="buildingName">Building Name:</label>
    <input type="text" id="buildingName" name="buildingName" required><br><br>

    <label for="street">Street:</label>
    <input type="text" id="street" name="street" required><br><br>

    <label for="city">City:</label>
    <input type="text" id="city" name="city" required><br><br>

    <label for="pinCode">Pin Code:</label>
    <input type="text" id="pinCode" name="pinCode" required><br><br>

    <label for="state">State:</label>
    <input type="text" id="state" name="state" required><br><br>

    <input type="submit" value="Submit">
</form>
</body>
</html>
