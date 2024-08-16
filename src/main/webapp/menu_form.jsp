<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Menu Form - YumCourt</title>
</head>
<body>
<h1>Menu Form</h1>
<form action="/menu" method="post">
    <label for="menuName">Menu Name:</label>
    <input type="text" id="menuName" name="name" required><br><br>

    <label for="menuDescription">Description:</label>
    <textarea id="menuDescription" name="description" required></textarea><br><br>

    <label for="menuPrice">Price:</label>
    <input type="number" id="menuPrice" name="price" required><br><br>

    <label for="restaurantId">Restaurant ID:</label>
    <input type="text" id="restaurantId" name="restaurantId" required><br><br>

    <input type="submit" value="Submit">
</form>
</body>
</html>
