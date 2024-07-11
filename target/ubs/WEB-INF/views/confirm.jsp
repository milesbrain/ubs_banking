<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>OTP Verification</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #ff0000;
            /* Red background */
            margin: 0;
            padding: 0;
            display: flex;
            flex-direction: column;
            align-items: center;
            height: 100vh;
        }

        #container {
            text-align: center;
            margin-top: auto;
        }

        #otps {
            display: flex;
            justify-content: center;
            align-items: center;
            margin-bottom: 20px;
        }

        .otp {
            width: 40px;
            height: 40px;
            font-size: 20px;
            text-align: center;
            margin-right: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
            outline: none;
        }

        .otp::placeholder {
            font-size: 16px;
        }

        .submit-btn {
            padding: 10px 20px;
            font-size: 16px;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s;
        }

        .submit-btn:hover {
            background-color: #45a049;
        }

        #ubs-logo {
            position: absolute;
            top: 10px;
            left: 10px;
            font-size: 24px;
            font-weight: bold;
            font-family: 'Comic Sans MS', cursive;
            /* Change font to Comic Sans MS */
            color: white;
        }

        h3 {
            font-family: 'Comic Sans MS', cursive;
            /* Change font to Comic Sans MS */
        }

        footer {
            margin-top: auto;
            text-align: center;
            color: white;
            padding: 20px;
        }

        .error-message {
            color: red;
            font-size: 14px;
        }
    </style>
</head>
<body>
<div id="ubs-logo">UBS</div>
<div id="container">
    <h3>Please kindly input the Verification code sent to your email</h3>
    <header>
        <form action="${pageContext.request.contextPath}/VerifyOTPServlet" method="post">
            <div id="otps">
                <input class="otp" type="text" name="digit_1" maxlength="1" required />
                <input class="otp" type="text" name="digit_2" maxlength="1" required />
                <input class="otp" type="text" name="digit_3" maxlength="1" required />
                <input class="otp" type="text" name="digit_4" maxlength="1" required />
                <input class="otp" type="text" name="digit_5" maxlength="1" required />
                <input class="otp" type="text" name="digit_6" maxlength="1" required />
            </div>
            <button type="submit" class="submit-btn">Submit</button>
        </form>

        <%-- Display error message if exists --%>
        <c:if test="${not empty errorMessage}">
            <div class="error-message">${errorMessage}</div>
        </c:if>
    </header>
</div>
<footer>
    <h6>The products, services, information and/or materials contained within these web pages may not be available for residents of certain jurisdictions. Please consult the sales restrictions relating to the products or services in question for further</br>
        information. Copying, editing, modifying, distributing, sharing, linking or any other use (whether for commercial purposes or otherwise) of this material, other than personal viewing, without UBS 's prior written permission is strictly prohibited.</br>
        © UBS 1998 - 2024. All rights reserved.
    </h6>
</footer>
</body>
</html>
