<%@ page import="java.time.LocalDateTime" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    LocalDateTime loginTime = (LocalDateTime) session.getAttribute("loginTime");
    String ipAddress = (String) session.getAttribute("ipAddress");
    String email = (String) session.getAttribute("email");

    if (loginTime == null || ipAddress == null || email == null) {
        request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
        return;
    }

    // Format the loginTime to a readable format
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    String formattedLoginTime = loginTime.format(formatter);
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Information</title>
    <style>
        body {
            margin: 0;
            padding: 0;
            font-family: 'Arial', sans-serif;
            background: linear-gradient(135deg, #ff9a9e 0%, #fad0c4 100%);
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        .container {
            display: flex;
            justify-content: center;
            align-items: center;
            width: 100%;
            max-width: 600px;
            padding: 20px;
        }

        .card {
            background: white;
            border-radius: 15px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
            overflow: hidden;
            text-align: center;
            padding: 20px;
            width: 100%;
        }

        .card h1 {
            font-size: 24px;
            margin-bottom: 20px;
            color: #333;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin: 20px 0;
        }

        th, td {
            padding: 10px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }

        th {
            background-color: #f2f2f2;
            color: #333;
            font-size: 18px;
        }

        td {
            font-size: 16px;
            background: #f5f5f5;
            border-radius: 10px;
            box-shadow: inset 0 1px 3px rgba(0, 0, 0, 0.1);
        }

        @media (max-width: 480px) {
            .card {
                padding: 15px;
            }
            .card h1 {
                font-size: 20px;
            }
            th, td {
                font-size: 14px;
                padding: 8px;
            }
        }
    </style>
</head>
<body>
<div class="container">
    <div class="card">
        <h1>User Information</h1>
        <table>
            <tr>
                <th>Information</th>
                <th>Details</th>
            </tr>
            <tr>
                <td>Email Address</td>
                <td><%= email %></td>
            </tr>
            <tr>
                <td>Login Time</td>
                <td><%= formattedLoginTime %></td>
            </tr>
            <tr>
                <td>IP Address</td>
                <td><%= ipAddress %></td>
            </tr>
            <!-- Add more rows as needed -->
        </table>
    </div>
</div>
</body>
</html>
