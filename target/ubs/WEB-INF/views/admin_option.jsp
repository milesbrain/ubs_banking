<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 5/31/2024
  Time: 11:24 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Options</title>
    <style>
        /* General Reset */

        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }
        /* Body Styling */

        body {
            font-family: 'Arial', sans-serif;
            background: linear-gradient(135deg, #ff9a9e 0%, #fad0c4 100%);
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        /* Container Styling */

        .container {
            text-align: center;
            background-color: rgba(255, 255, 255, 0.9);
            padding: 40px;
            border-radius: 10px;
            box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
            max-width: 600px;
            width: 100%;
        }
        /* Header Styling */

        header h1 {
            font-size: 2em;
            margin-bottom: 20px;
            color: #333;
        }
        /* Button Container */

        .buttons {
            display: flex;
            flex-direction: column;
            gap: 15px;
        }
        /* Button Styling */

        button.button {
            display: inline-block;
            padding: 15px 30px;
            font-size: 1.2em;
            color: #fff;
            background-color: #007bff;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s, transform 0.3s;
        }

        button.button:hover {
            background-color: #b30000;
            transform: scale(1.05);
        }
    </style>
</head>

<body>
<div class="container">
    <header>
        <h1>What do you want as an admin?</h1>
    </header>
    <div class="buttons">
        <form action="${pageContext.request.contextPath}/open_account"><button type="submit" class="button">Open an account</button></form>
        <form action="${pageContext.request.contextPath}/address"><button type="submit" class="button">Account Status</button></form>
        <form action="option3.jsp"><button type="submit" class="button">Option 3</button></form>
        <form action="option4.jsp"><button type="submit" class="button">Option 4</button></form>
    </div>
</div>
</body>

</html>