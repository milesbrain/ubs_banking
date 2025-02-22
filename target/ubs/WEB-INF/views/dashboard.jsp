<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Bank Dashboard Concept</title>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/meyer-reset/2.0/reset.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/dashboard.css" />
    <script>
        function formatBalance(balance, currency) {
            let formatter = new Intl.NumberFormat('en-US', {
                style: 'currency',
                currency: currency,
                minimumFractionDigits: 2,
            });
            return formatter.format(balance);
        }

        document.addEventListener('DOMContentLoaded', function () {
            let balanceElement = document.getElementById('account-balance');
            let balance = parseFloat(balanceElement.getAttribute('data-balance'));
            let currency = balanceElement.getAttribute('data-currency');
            balanceElement.innerText = formatBalance(balance, currency);
        });
    </script>
</head>

<body>
<div class="app">
    <header class="app-header">
        <div class="app-header-logo">
            <div class="logo">
                <span class="logo-icon">
                    <img src="ubs.jpeg" alt="UBS Logo"/>
                </span>
                <h1 class="logo-title">
                    <span>Welcome Back, ${accountName}</span>
                </h1>
            </div>
        </div>
        <div class="app-header-navigation">
            <div class="tabs">
                <a href="#">Overview</a>
                <a href="#" class="active">Payments</a>
                <a href="#">Cards</a>
                <a href="#">Account</a>
                <a href="#">System</a>
                <a href="#">Business</a>
            </div>
        </div>
        <div class="app-header-actions">
            <button class="user-profile">
                <span>Union de Banques Suisses</span>
                <span>
                    <img src="https://assets.codepen.io/285131/almeria-avatar.jpeg" alt="User Avatar"/>
                </span>
            </button>
            <div class="app-header-actions-buttons">
                <button class="icon-button large">
                    <i class="ph-magnifying-glass"></i>
                </button>
                <button class="icon-button large">
                    <i class="ph-bell"></i>
                </button>
                <a href="${pageContext.request.contextPath}/" class="logout-button">Log Out</a>
            </div>
        </div>
        <div class="app-header-mobile">
            <button class="icon-button large">
                <i class="ph-list"></i>
            </button>
        </div>
    </header>

    <div class="app-body">
        <div class="app-body-navigation">
            <nav class="navigation">
                <a href="#">
                    <i class="ph-browsers"></i>
                    <span>Dashboard</span>
                </a>
                <a href="#">
                    <i class="ph-check-square"></i>
                    <span>Scheduled</span>
                </a>
                <a href="#">
                    <i class="ph-swap"></i>
                    <span>Transfers</span>
                </a>
                <a href="#">
                    <i class="ph-file-text"></i>
                    <span>Templates</span>
                </a>
                <a href="#">
                    <i class="ph-globe"></i>
                    <span>SWIFT</span>
                </a>
                <a href="#">
                    <i class="ph-clipboard-text"></i>
                    <span>Exchange</span>
                </a>
            </nav>
            <footer class="footer">
                <h1>UBS<small>©</small></h1>
                <div>UBS © All Rights Reserved 2021</div>
            </footer>
        </div>

        <div class="balance-wrap">
            <h3>Joint Account</h3>
            <div class="balance">
                <h1 id="account-balance" data-balance="${balance}" data-currency="${currency}">${currency}${balance}</h1>
                <a href="#">Send Money</a>
            </div>
        </div>


        <div class="app-body-main-content">
            <section class="service-section">
                <h2>Service</h2>
                <div class="service-section-header">
                    <div class="search-field">
                        <i class="ph-magnifying-glass"></i>
                        <input type="text" placeholder="Account number">
                    </div>
                    <div class="dropdown-field">
                        <select>
                            <option>Home</option>
                            <option>Work</option>
                        </select>
                        <i class="ph-caret-down"></i>
                    </div>
                    <button class="flat-button">Search</button>
                </div>
                <div class="mobile-only">
                    <button class="flat-button">Toggle search</button>
                </div>
                <div class="tiles">
                    <article class="tile">
                        <div class="tile-header">
                            <i class="ph-lightning-light"></i>
                            <h3>
                                <span>Electricity</span>
                                <span>UrkEnergo LTD.</span>
                            </h3>
                        </div>
                        <a href="#">
                            <span>Go to service</span>
                            <span class="icon-button">
                                <i class="ph-caret-right-bold"></i>
                            </span>
                        </a>
                    </article>
                    <article class="tile">
                        <div class="tile-header">
                            <i class="ph-fire-simple-light"></i>
                            <h3>
                                <span>Heating Gas</span>
                                <span>Gazprom UA</span>
                            </h3>
                        </div>
                        <a href="#">
                            <span>Go to service</span>
                            <span class="icon-button">
                                <i class="ph-caret-right-bold"></i>
                            </span>
                        </a>
                    </article>
                    <article class="tile">
                        <div class="tile-header">
                            <i class="ph-file-light"></i>
                            <h3>
                                <span>Tax online</span>
                                <span>Kharkov 62 str.</span>
                            </h3>
                        </div>
                        <a href="#">
                            <span>Go to service</span>
                            <span class="icon-button">
                                <i class="ph-caret-right-bold"></i>
                            </span>
                        </a>
                    </article>
                </div>
                <div class="service-section-footer">
                    <p>Services are paid according to the current state of the currency and tariff.</p>
                </div>
            </section>

            <section class="transfer-section">
                <div class="transfer-section-header">
                    <h2>Latest Transfers</h2>
                    <div class="filter-options">
                        <p>Filter selected: more than 100 $</p>
                        <button class="icon-button">
                            <i class="ph-funnel"></i>
                        </button>
                        <button class="icon-button">
                            <i class="ph-plus"></i>
                        </button>
                    </div>
                </div>
                <div class="transfers">
                    <div class="transfer">
                        <div class="transfer-logo">
                            <img src="https://assets.codepen.io/285131/apple.svg" alt="Apple Inc." />
                        </div>
                        <dl class="transfer-details">
                            <div>
                                <dt>Apple Inc.</dt>
                                <dd>Apple ID Payment</dd>
                            </div>
                            <div>
                                <dt>4012</dt>
                                <dd>Last four digits</dd>
                            </div>
                            <div>
                                <dt>28 Oct. 21</dt>
                                <dd>Date payment</dd>
                            </div>
                        </dl>
                        <div class="transfer-number">
                            - $ 550
                        </div>
                    </div>
                    <div class="transfer">
                        <div class="transfer-logo">
                            <img src="https://assets.codepen.io/285131/pinterest.svg" alt="Pinterest" />
                        </div>
                        <dl class="transfer-details">
                            <div>
                                <dt>Pinterest</dt>
                                <dd>2 year subscription</dd>
                            </div>
                            <div>
                                <dt>5214</dt>
                                <dd>Last four digits</dd>
                            </div>
                            <div>
                                <dt>26 Oct. 21</dt>
                                <dd>Date payment</dd>
                            </div>
                        </dl>
                        <div class="transfer-number">
                            - $ 120
                        </div>
                    </div>
                    <div class="transfer">
                        <div class="transfer-logo">
                            <img src="https://assets.codepen.io/285131/warner-bros.svg" alt="Warner Bros." />
                        </div>
                        <dl class="transfer-details">
                            <div>
                                <dt>Warner Bros.</dt>
                                <dd>Cinema</dd>
                            </div>
                            <div>
                                <dt>2228</dt>
                                <dd>Last four digits</dd>
                            </div>
                            <div>
                                <dt>22 Oct. 21</dt>
                                <dd>Date payment</dd>
                            </div>
                        </dl>
                        <div class="transfer-number">
                            - $ 70
                        </div>
                    </div>
                </div>
            </section>
        </div>

        <div class="app-body-sidebar">
            <section class="payment-section">
                <h2>New Payment</h2>
                <div class="payment-section-header">
                    <p>From</p>
                    <div class="dropdown-field">
                        <select>
                            <option>${accountType}</option>
                        </select>
                        <i class="ph-caret-down"></i>
                    </div>
                </div>
                <div class="payment-section-content">
                    <div class="content-step">
                        <p>To</p>
                        <div class="dropdown-field">
                            <select>
                                <option>Select account</option>
                            </select>
                            <i class="ph-caret-down"></i>
                        </div>
                    </div>
                    <div class="content-step">
                        <p>Amount</p>
                        <div class="amount-field">
                            <input type="text" value="${currency}">
                            <input type="text" placeholder="0.00">
                        </div>
                    </div>
                    <div class="content-step">
                        <p>Reference</p>
                        <input type="text">
                    </div>
                    <div class="content-step">
                        <p>When</p>
                        <div class="dropdown-field">
                            <select>
                                <option>Now</option>
                            </select>
                            <i class="ph-caret-down"></i>
                        </div>
                    </div>
                </div>
                <div class="payment-section-footer">
                    <button class="primary-button">Pay now</button>
                </div>
            </section>
        </div>
    </div>
</div>

<script src="https://cdnjs.cloudflare.com/ajax/libs/tiny-slider/2.9.3/min/tiny-slider.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/phosphor-icons/1.4.1/phosphor.min.js"></script>
</body>
</html>
