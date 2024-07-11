<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 6/1/2024
  Time: 12:24 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Open an Account</title>
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
            background: linear-gradient(135deg, #a8edea 0%, #fed6e3 100%);
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        /* Container Styling */
        .container {
            background-color: rgba(255, 255, 255, 0.9);
            padding: 40px;
            border-radius: 10px;
            box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
            max-width: 500px;
            width: 100%;
            text-align: center;
        }
        /* Header Styling */
        header h1 {
            font-size: 2em;
            margin-bottom: 20px;
            color: #333;
        }
        /* Form Styling */
        .account-form {
            display: flex;
            flex-direction: column;
        }
        /* Form Group Styling */
        .form-group {
            margin-bottom: 15px;
        }
        /* Label Styling */
        label {
            font-size: 1.1em;
            margin-bottom: 5px;
            display: block;
            color: #555;
        }
        /* Input and Select Styling */
        input, select {
            width: 100%;
            padding: 10px;
            font-size: 1em;
            border: 1px solid #ccc;
            border-radius: 5px;
            transition: border-color 0.3s;
        }
        input:focus, select:focus {
            border-color: #007bff;
            outline: none;
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
            background-color: #0056b3;
            transform: scale(1.05);
        }
        /* Currency Group Styling */
        .currency-group {
            display: flex;
            align-items: center;
        }
        .currency-group select {
            width: auto;
            margin-left: 10px;
        }
    </style>
    <script>
        function formatCurrency(input) {
            let value = input.value.replace(/,/g, '');
            if (!isNaN(value) && value !== '') {
                let formattedValue = parseFloat(value).toLocaleString('en-US', {
                    minimumFractionDigits: 2,
                    maximumFractionDigits: 2
                });
                input.value = formattedValue;
            } else {
                input.value = '';
            }
        }
    </script>
</head>
<body>
<div class="container">
    <header>
        <h1>Open a New Account</h1>
    </header>
    <div class="message">
        <c:if test="${not empty successMessage}">
            <p>${successMessage}</p>
        </c:if>
    </div>
    <form action="${pageContext.request.contextPath}/submit_account" method="post" class="account-form">
        <div class="form-group">
            <label for="account-type">Bank Account Type</label>
            <select id="account-type" name="accountType" required>
                <option value="" disabled selected>Select your account type</option>
                <option value="Savings Account">Savings Account</option>
                <option value="Checking Account">Checking Account</option>
                <option value="Business Account">Business Account</option>
                <option value="Recurring Deposit Account">Recurring Deposit Account</option>
                <option value="Fixed Deposit Account">Fixed Deposit Account</option>
                <option value="Joint Account">Joint Account</option>
                <option value="Student Saving Account">Student Saving Account</option>
                <option value="NRI Account">NRI Account</option>
                <option value="Retirement Account">Retirement Account</option>
            </select>
        </div>
        <div class="form-group">
            <label for="account-name">Account Name</label>
            <input type="text" id="account-name" name="accountName" required>
        </div>
        <div class="form-group">
            <label for="email">Email</label>
            <input type="email" id="email" name="email" required>
        </div>
        <div class="form-group">
            <label for="password">Password</label>
            <input type="password" id="password" name="password" required>
        </div>
        <div class="form-group">
            <label for="amount">Initial Deposit Amount</label>
            <div class="currency-group">
                <input type="text" id="amount" name="amount" required onblur="formatCurrency(this)">
                <select name="currency" id="currency" required>
                    <option value="USD" selected>USD ($)</option>
                    <option value="EUR">EUR (€)</option>
                    <option value="GBP">GBP (£)</option>
                </select>
            </div>
        </div>
        <div class="form-group">
            <button type="submit" class="button">Open Account</button>
        </div>
    </form>
</div>
</body>
</html>
