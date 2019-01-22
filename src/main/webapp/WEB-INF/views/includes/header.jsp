<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">

<head>
    <!-- Meta Data -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>${empty pageTitle ? 'title.home':pageTitle}</title>

    <!-- Dependency Styles -->
    <link rel="stylesheet" href="/dependencies/loaders.css/loaders.min.css" type="text/css">
    <link rel="stylesheet" href="/dependencies/bootstrap/css/bootstrap.min.css" type="text/css">
    <link rel="stylesheet" href="/dependencies/font-awesome/css/font-awesome.min.css" type="text/css">
    <link rel="stylesheet" href="/dependencies/Swiper/css/swiper.min.css" type="text/css">
    <link rel="stylesheet" href="/dependencies/animate.css/animate.min.css" type="text/css">
    <link rel="stylesheet" href="/dependencies/magnific-popup/magnific-popup.css" type="text/css">

    <!-- Site Stylesheet -->
    <link rel="stylesheet" href="/assets/css/app.css" type="text/css">
    <link id="theme" rel="stylesheet" href="/assets/css/themes/sky.css" type="text/css">

    <!-- App Icons -->
    <link rel="apple-touch-icon" sizes="180x180" href="/assets/img/logo.png">
    <link rel="icon" type="image/png" href="/assets/img/logo.png" sizes="32x32">
    <link rel="icon" type="image/png" href="/assets/img/logo.png" sizes="16x16">
    <link rel="manifest" href="/assets/img/app-icons/manifest.json">
    <link rel="mask-icon" href="/assets/img/app-icons/safari-pinned-tab.svg" color="#fd367e">
    <meta name="theme-color" content="#ffffff">

    <!-- Google Web Fonts -->
    <link href="//fonts.googleapis.com/css?family=Josefin+Sans:600%7CRoboto:400,500,700" rel="stylesheet">


    <!--[if lt IE 9]>
    <script src="//oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="//oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body id="dt-home-version-5" class="dt-home-version-5" data-style="default">

<!-- Loader -->
<div id="site-preloader">
    <div class="loader-inner ball-scale-multiple"></div>
</div>
<!-- /#site-preloader -->

<div id="site">


    <!--==============================-->
    <!--=        Site Header         =-->
    <!--==============================-->
    <header id="site-header" class="site-header">


        <nav class="navbar dt-standard-navbar">
            <div class="container">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                            data-target="#dt-primary-navigation" aria-expanded="false">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar top-bar"></span>
                        <span class="icon-bar middle-bar"></span>
                        <span class="icon-bar bottom-bar"></span>
                    </button>
                    <a class="navbar-brand" href="/">
                        <img class="white-logo" src="/assets/img/logo.png" alt="Devent">
                        <img class="black-logo" src="/assets/img/logo.png" alt="Devent">
                    </a>
                </div>

                <div class="collapse navbar-collapse" id="dt-primary-navigation">
                    <c:if test="${not empty menu}">
                        ${menu}
                    </c:if>
                </div>
            </div>
        </nav>
    </header>

    <div id="header-fake-mask"></div>
