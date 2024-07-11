<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en-us" data-arp-injected="true">
<head>
    <title>UBS Online Services</title>
    <meta charset="utf-8">
    <meta name="description" content="UBS Online Services">
    <meta name="author" content="UBS">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1">

    <!-- Adjust the path to the CSS file -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css">
</head>

<body onload="init();" class="login" monica-version="5.1.0" monica-id="ofpnmcalabcbjgholdjcjblkibolbppb">
<header role="banner">
    <nav class="masthead">
        <a class="brand" title="" href="/">Home</a>
    </nav>
</header>
<div class="wma">
    <div class="App ols-portal ">
        <!-- ReactNoti -->
        <div class="ReactNoti wma"></div>
        <header class="Header" data-testid="header">
            <a href="/"><img class="ubs-logo" src="${pageContext.request.contextPath}/images/imageubs.jpeg" alt="UBS logo"></a>
        </header>
        <div class="Login">
            <div class="OlsHeaderBackground"></div>
            <div class="Login__content">
                <header class="Login__header">
                    <h1>Welcome to UBS, please sign in.</h1>
                </header>
                <div class="Login__actions">
                    <form action="LoginServlet" method="post">
                        <fieldset class="LoginForm__fieldset">
                            <div class="InputField">
                                <label for="email">
                                    <input class=""  id="email" type="text" name="email" value="">
                                    <span class="InputField__placeholder">Email</span>
                                </label>
                            </div>
                            <div class="InputField">
                                <label for="password">
                                    <input class="input-password" id="password" data-testid="password" type="password" name="password" maxlength="64" value="">
                                    <span class="InputField__placeholder">Password</span>
                                </label>
                                <button class="show-password-btn" type="button" data-testid="show-password-btn" onclick="togglePasswordVisibility()">Show Password</button>
                                <c:if test="${not empty errorMessage}">
                                    <div class="error-message">${errorMessage}</div>
                                </c:if>
                            </div>
                        </fieldset>
                        <div class="LoginForm__actions">
                            <button class="wma-btn wma-btn-primary sign-in-button" type="submit">Sign In</button>
                            <label class="remember-me" for="remember-me-checkbox">
                                <input class="wma-checkbox" id="remember-me-checkbox" type="checkbox" name="rememberMe" data-testid="remember-me-checkbox"> Remember me
                            </label>
                        </div>
                        <div class="credentials-recovery">Forgot my <a href="#/forgot-username">username</a> or&nbsp;<a href="#/forgot-password">password</a></div>
                        <div class="LoginForm__footer" data-testid="login-form-footer">
                            <div class="user-registration">
                                <h4>First time user?</h4>
                                <a href="${pageContext.request.contextPath}/index"  class="user-registration__link"  data-testid="user-registration-btn">Login as an Admin</a>
                            </div>
                            <div class="broker-check" data-testid="finra-broker-check">
                                View&nbsp;<a href="https://www.ubs.com/us/en/wealth-management/information/you-are-being-redirected-to-brokercheck-org.html" target="_blank" rel="noopener noreferrer">FINRA BrokerCheck</a>&nbsp;info
                            </div>
                        </div>
                    </form>
                    <div class="LoginDivider">
                        <p>OR</p>
                    </div>
                    <div class="LoginWithQRCode">
                        <h4>Experience a convenient way to sign in</h4>
                        <div class="LoginWithQRCode__content">
                            <section class="LoginWithQRCode__instruction">
                                <p class="LoginWithQRCode__subtitle">Improved security without passwords</p>
                                <p>Have your UBS Financial Services app ready and scan a unique QR code to securely sign in.</p>
                            </section>
                            <section class="LoginWithQRCode__imgpanel">
                                <img src="/olsauth/public/common/nlp/images/qr-on-mobile.svg" alt="">
                            </section>
                        </div>
                    </div>
                    <div id="qrCodeContainer" style="display: none;"></div>
                </div>
            </div>
        </div>
        <footer class="Footer" data-testid="footer">
            <div class="footer-caption">
                <div class="footer-caption__notice">Wealth management services in the United States are provided by UBS Financial Services Inc. Member <a href="https://www.sipc.org/" target="_blank" rel="noreferrer">SIPC</a></div>
                <div class="footer-caption__copyright">© UBS 1998–2024. All rights reserved.</div>
            </div>
            <div class="footer-links">
                <div class="footer-links__privacy footer-links__block">
                    <a target="_blank" href="#/privacy">Privacy &amp; Security</a>
                    <a href="https://www.ubs.com/relationshipwithubs">Your Relationship with UBS</a>
                    <a href="https://www.ubs.com/relationshipsummary">Relationship Summary</a>
                    <button class="wma-btn wma-btn-link" type="button" data-testid="disclosures-btn">Disclosures</button>
                    <button class="wma-btn wma-btn-link" type="button" data-testid="support-btn">Support</button>
                </div>
                <div class="footer-links__block footer-links__fsi">
                    Statement of Financial Condition: <a href="https://onlineservices.ubs.com/staticfiles/pws/adobe/StatementofFinancialCondition.pdf">UBS FSI</a>
                </div>
            </div>
        </footer>
    </div>
</div>

<script>
    function togglePasswordVisibility() {
        var passwordInput = document.getElementById("password");
        var passwordButton = document.getElementById("show-password-btn");
        if (passwordInput.type === "password") {
            passwordInput.type = "text";
            passwordButton.innerHTML = "Hide Password";
        } else {
            passwordInput.type = "password";
            passwordButton.innerHTML = "Show Password";
        }
    }
</script>

</body>
</html>
