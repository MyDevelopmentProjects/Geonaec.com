<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="HtmlTruncator" uri="/WEB-INF/tlds/HtmlTruncator" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:useBean id="dateValue" class="java.util.Date"/>
<!--==============================-->
<!--= Site Banner =-->
<!--==============================-->
<c:if test="${not empty topSlider}">
    <section class="site-banner transparent-area" data-bg-image="uploads/${topSlider.image}">
        <div class="banner-overlay slider-style-two"> <!--data-bg-color="rgba(5, 7, 9, 0.5)"-->
            <div class="banner-content">
                <div class="container">
                    <h3 class="title-two">${topSlider.title1}</h3>
                    <h2 class="title">${topSlider.title2}</h2>
                    <div class="description">
                            ${topSlider.description}
                    </div>
                </div>
            </div>
        </div>
    </section>
</c:if>
<!-- /.site-banner -->

<!--=================================-->
<!--= Event Schedule =-->
<!--=================================-->

<c:if test="${not empty topEvents}">
    <section id="site-event-schedule" class="site-event-schedule section-padding-top style-two">
        <div class="container" data-bg-image="uploads/${topEvents.image}">
            <div class="row">
                <div class="col-md-4">
                    <div class="event-schedule-info sr-animated" style="padding: 20px;">
                        <div class="section-heading">
                            <h2 class="title">${topEvents.pageTitle} <span class="effect"></span></h2>
                        </div>
                        <div class="info-content">
                                ${topEvents.pageDesc}
                        </div>
                    </div>
                </div>
                <div class="col-md-7 col-md-offset-1">
                    <div class="event-schedule-tabs sr-animated">
                        <ul class="nav nav-tabs" role="tablist">
                            <li role="presentation" class="active">
                                <a href="#event-tab-day-01"
                                   aria-controls="event-tab-day-01"
                                   role="tab" data-toggle="tab">
                                    <fmt:formatDate value="${topEvents.date1}" pattern="MM-dd-yyyy"/>
                                </a>
                            </li>
                            <c:if test="${not empty topEvents.date2}">
                                <li role="presentation">
                                    <a href="#event-tab-day-02"
                                       aria-controls="event-tab-day-02"
                                       role="tab" data-toggle="tab">
                                        <fmt:formatDate value="${topEvents.date2}" pattern="MM-dd-yyyy"/>
                                    </a>
                                </li>
                            </c:if>
                            <c:if test="${not empty topEvents.date3}">
                                <li role="presentation">
                                    <a href="#event-tab-day-03"
                                       aria-controls="event-tab-day-03"
                                       role="tab" data-toggle="tab">
                                        <fmt:formatDate value="${topEvents.date3}" pattern="MM-dd-yyyy"/>
                                    </a>
                                </li>
                            </c:if>
                        </ul>
                        <div class="tab-content">

                            <div role="tabpanel" class="tab-pane fade in active" id="event-tab-day-01">
                                <div class="panel-group" id="event-schedule-collapse-day-01" role="tablist"
                                     aria-multiselectable="true">
                                    <div class="panel panel-default">
                                        <div class="panel-heading" role="tab"
                                             id="event-schedule-accordion-title-day-01-01">
                                            <h4 class="panel-title">
                                                <a role="button" data-toggle="collapse"
                                                   data-parent="#event-schedule-collapse-day-01"
                                                   href="#event-schedule-accordion-content-day-01-01"
                                                   aria-expanded="true"
                                                   aria-controls="event-schedule-accordion-content-day-01-01">
                                                <span class="time">
                                                    <i class="fa fa-clock-o"></i>
                                                    <fmt:formatDate value="${topEvents.date1}" pattern="MM-dd-yyyy"/>
                                                </span>
                                                    <span class="title">
                                                            ${topEvents.title1}
                                                    </span>
                                                </a>
                                            </h4>
                                        </div>
                                        <div id="event-schedule-accordion-content-day-01-01"
                                             class="panel-collapse collapse in" role="tabpanel"
                                             aria-labelledby="event-schedule-accordion-title-day-01-01">
                                            <div class="panel-body">
                                                <p class="description">
                                                    ${topEvents.description1}
                                                </p>
                                            </div>
                                            <!-- /.panel-body -->
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <c:if test="${not empty topEvents.date2}">
                                <div role="tabpanel" class="tab-pane fade" id="event-tab-day-02">
                                    <div class="panel-group" id="event-schedule-collapse-day-02" role="tablist"
                                         aria-multiselectable="true">
                                        <div class="panel panel-default">
                                            <div class="panel-heading" role="tab"
                                                 id="event-schedule-accordion-title-day-02-01">
                                                <h4 class="panel-title">
                                                    <a role="button" data-toggle="collapse"
                                                       data-parent="#event-schedule-collapse-day-02"
                                                       href="#event-schedule-accordion-content-day-02-01"
                                                       aria-expanded="true"
                                                       aria-controls="event-schedule-accordion-content-day-02-01">
                                                    <span class="time">
                                                    <i class="fa fa-clock-o"></i>
                                                        <fmt:formatDate value="${topEvents.date2}" pattern="MM-dd-yyyy"/>
                                                    </span>
                                                        <span class="title">${topEvents.title2}</span>
                                                    </a>
                                                </h4>
                                            </div>
                                            <!-- /.panel-heading -->
                                            <div id="event-schedule-accordion-content-day-02-01"
                                                 class="panel-collapse collapse in" role="tabpanel"
                                                 aria-labelledby="event-schedule-accordion-title-day-02-01">
                                                <div class="panel-body">
                                                    <p class="description">
                                                        ${topEvents.description2}
                                                    </p>
                                                    <!-- /.description-->
                                                </div>
                                                <!-- /.panel-body -->
                                            </div>
                                            <!-- /.panel-collapse -->
                                        </div>
                                        <!-- /.panel -->
                                    </div>
                                    <!-- /#event-schedule-collapse-day-02 -->
                                </div>
                            </c:if>

                            <c:if test="${not empty topEvents.date3}">
                                <div role="tabpanel" class="tab-pane fade" id="event-tab-day-03">
                                    <div class="panel-group" id="event-schedule-collapse-day-03" role="tablist"
                                         aria-multiselectable="true">
                                        <div class="panel panel-default">
                                            <div class="panel-heading" role="tab"
                                                 id="event-schedule-accordion-title-day-03-01">
                                                <h4 class="panel-title">
                                                    <a role="button" data-toggle="collapse"
                                                       data-parent="#event-schedule-collapse-day-03"
                                                       href="#event-schedule-accordion-content-day-03-01"
                                                       aria-expanded="true"
                                                       aria-controls="event-schedule-accordion-content-day-03-01">
                                                    <span class="time">
                                                    <i class="fa fa-clock-o"></i>
                                                        <fmt:formatDate value="${topEvents.date3}" pattern="MM-dd-yyyy"/>
                                                    </span>
                                                    <span class="title">${topEvents.title3}</span>
                                                    </a>
                                                </h4>
                                            </div>
                                            <!-- /.panel-heading -->
                                            <div id="event-schedule-accordion-content-day-03-01"
                                                 class="panel-collapse collapse in" role="tabpanel"
                                                 aria-labelledby="event-schedule-accordion-title-day-03-01">
                                                <div class="panel-body">
                                                    <p class="description">
                                                        ${topEvents.description3}
                                                    </p>
                                                    <!-- /.description-->
                                                </div>
                                                <!-- /.panel-body -->
                                            </div>
                                            <!-- /.panel-collapse -->
                                        </div>
                                        <!-- /.panel -->

                                    </div>
                                    <!-- /#event-schedule-collapse-day-03 -->
                                </div>
                            </c:if>

                        </div>
                        <!-- /.tab-content -->
                    </div>
                    <!-- /.event-schedule-tabs -->
                </div>
            </div>
            <hr class="mt-50">
        </div>
    </section>
</c:if>
<!-- /#site-event-schedule -->

<!--==================================-->
<!--= About The Event =-->
<!--==================================-->
<c:if test="${not empty about}">
    <section id="site-about-the-event" class="site-about-the-event section-padding">
        <div class="container">
            <div class="section-heading text-center sr-animated">
                <h2 class="title">${about.pageTitle} <span class="effect"></span></h2>
            </div>
            <div class="about-the-event sr-animated">
                <!-- Nav tabs -->
                <ul class="nav nav-tabs" role="tablist">
                    <li role="presentation">
                        <a href="#about-the-event-video-tab"
                           aria-controls="about-the-event-video-tab"
                           role="tab"
                           data-toggle="tab">
                                ${about.tabTitleLeft}
                        </a>
                    </li>
                    <li role="presentation" class="active">
                        <a href="#about-the-event-photo-tab"
                           aria-controls="about-the-event-photo-tab"
                           role="tab"
                           data-toggle="tab">
                                ${about.tabTitleRight}
                        </a>
                    </li>
                </ul>
                <div class="tab-content">
                    <div role="tabpanel" class="tab-pane fade" id="about-the-event-video-tab">
                        <div class="row">
                            <div class="col-md-6">
                                <div class="event-image-carousel">
                                    <div class="swiper-container">
                                        <div class="swiper-wrapper">
                                            <div class="swiper-slide">
                                                <img src="media/images/about-the-event/carousel/01.jpg" alt="Image"
                                                     width="584" height="320">
                                                <a href="${about.videoUrl}" class="dt-play-btn"
                                                   data-lighbox-iframe>
                                                    <i class="fa fa-play" aria-hidden="true"></i>
                                                </a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!-- /.col-md-6 -->
                            <div class="col-md-6">
                                <div class="about-content-text-block">
                                    <h3>${about.title1}</h3>
                                    <p class="description">
                                            ${about.description1}
                                    </p>
                                </div>
                            </div>
                            <!-- /.col-md-6 -->
                        </div>
                        <!-- /.row -->
                    </div>
                    <!-- /.tabpanel -->
                    <div role="tabpanel" class="tab-pane fade in active" id="about-the-event-photo-tab">
                        <div class="row">
                            <div class="col-md-6">
                                <div class="about-content-text-block">
                                    <h3>${about.title2}</h3>
                                    <p class="description">
                                            ${about.description2}
                                    </p>

                                        <%--<a href="#" class="btn btn-devent btn-dt btn-hs">LEARN MORE</a>--%>
                                </div>
                            </div>
                            <!-- /.col-md-6 -->
                            <div class="col-md-6">
                                <div class="dynamic-photo-gallery" data-dynamic-gallery>
                                    <div class="photo-item">
                                        <a href="uploads/${about.image1}" title="Image 01">
                                            <img src="uploads/${about.image1}" alt="Image 01">
                                        </a>
                                    </div>
                                    <!-- /.photo-item -->
                                    <div class="photo-item">
                                        <a href="uploads/${about.image2}" title="Image 02">
                                            <img src="uploads/${about.image2}" alt="Image 02"
                                                 width="282" height="177">
                                        </a>
                                    </div>
                                    <!-- /.photo-item -->
                                    <div class="photo-item">
                                        <a href="uploads/${about.image3}" title="Image 03">
                                            <img src="uploads/${about.image3}" alt="Image 03"
                                                 width="282" height="177">
                                        </a>
                                    </div>
                                    <!-- /.photo-item -->
                                    <div class="photo-item">
                                        <a href="uploads/${about.image4}" title="Image 04">
                                            <img src="uploads/${about.image4}" alt="Image 04"
                                                 width="282" height="177">
                                        </a>
                                    </div>
                                    <!-- /.photo-item -->
                                </div>
                                <!-- /.dynamic-photo-gallery -->
                            </div>
                            <!-- /.col-md-6 -->
                        </div>
                        <!-- /.row -->
                    </div>
                    <!-- /.tabpanel -->
                </div>
            </div>
        </div>
    </section>
</c:if>

<!--=============================-->
<!--= Newsletter =-->
<!--=============================-->
<c:if test="${not empty separator}">
    <section id="site-newsletter" class="site-newsletter section-padding transparent-area"
             data-bg-image="uploads/${separator.image}" data-parallax="image">
        <div class="container">
            <div class="section-heading text-center">
                <h2 class="title">
                        ${separator.title}
                    <span class="effect"></span>
                </h2>
            </div>
            <!-- /.section-heading -->

            <div class="transparent-form">
            </div>
            <!-- /.transparent-form -->
        </div>
        <!-- /.container -->
    </section>
</c:if>

<!--=================================-->
<!--= Event Speakers =-->
<!--=================================-->
<c:if test="${not empty scroll}">
    <section id="site-event-speakers" class="site-event-speakers section-padding" data-carousel="swiper">\
        <div class="container">
            <div class="section-heading sr-animated text-center">
               <%-- <h2 class="title">Event Speakers <span class="effect"></span></h2>--%>
            </div>
        </div>
        <div class="container speakers-profile-carousel">
            <div class="event-speaker-profile swiper-container"
                 data-swiper="container"
                 data-loop="true"
                 data-looped="8">
                <div class="swiper-wrapper">
                    <c:forEach items="${scroll}" var="item">
                        <div class="swiper-slide">
                            <div class="speaker-profile-details">
                                <div class="profile-image" data-animate="fadeInUp" data-delay="0.2s"
                                     data-duration="0.5s">
                                    <div style="background: url(uploads/${item.image});
                                                 width: 480px !important;
                                                 height: 460px !important;
                                                 background-position: center center;
                                                 background-size: cover;"
                                          alt="${item.title}"></div>
                                </div>
                                <!-- /.profile-image -->

                                <div class="profile-content">
                                    <div class="name-job" data-animate="fadeInUp" data-delay="0.4s"
                                         data-duration="0.5s">
                                        <h3 class="name">${item.title}</h3>
                                        <div class="job">${item.subTitle}</div>
                                    </div>
                                    <!-- /.name-job -->

                                    <div class="biography" data-animate="fadeInUp" data-delay="0.6s"
                                         data-duration="0.5s">
                                            ${item.description}
                                                <c:if test="${fn:length(item.array) > 0}">
                                                    <p style="margin-top: 10px; border-top: solid 1px #e5e5e5;">
                                                        <span>Attached Files</span><br />
                                                        <c:forEach items="${item.array}" var="element" varStatus="loop">
                                                            <a href="uploads/${element}"
                                                               target="_blank">${element}</a><br/>
                                                        </c:forEach>
                                                    </p>
                                                </c:if>
                                    </div>
                                    <!-- /.biography -->

                                </div>
                                <!-- /.profile-content -->
                            </div>
                            <!-- /.speaker-profile-details -->
                        </div>
                    </c:forEach>
                </div>

            </div>
            <div class="event-speakers-list swiper-container"
                 data-swiper="ascontrol"
                 data-items="3"
                 data-center="true"
                 data-space="30"
                 data-click-to-slide="true"
                 data-loop="true"
                 data-looped="8"
                 data-breakpoints='{"5000": {"slidesPerView": 4}, "1024": {"slidesPerView": 4}, "768": {"slidesPerView": 2}, "500": {"slidesPerView": 1} }'>

                <div class="swiper-wrapper">
                    <c:forEach items="${scroll}" var="item">
                        <div class="swiper-slide">
                            <div class="speaker-profile-thumb">
                                <img style="background: url(uploads/${item.image});
                                        width: 270px !important;
                                        height: 450px !important;
                                        background-position: center center;
                                        background-size: cover;">

                                <div class="profile-info">
                                    <div class="name-job">
                                        <div class="name">
                                            ${item.title}
                                        </div>
                                        <!-- /.name -->
                                        <div class="job">
                                            ${item.subTitle}
                                        </div>
                                        <!-- /.job -->
                                    </div>
                                    <!-- /.name-job -->

                                    <p class="description">
                                    <p>${item.subTitle}</p>
                                    </p>
                                    <!-- /.description -->

                                </div>
                                <!-- /.profile-info -->
                            </div>
                            <!-- /.speaker-profile-thumb -->
                        </div>
                    </c:forEach>
                </div>

                <div class="carousel-button-prev" data-swiper="prev">
                    <i class="fa fa-angle-left"></i>
                </div>
                <div class="carousel-button-next" data-swiper="next">
                    <i class="fa fa-angle-right"></i>
                </div>

            </div>
            <!-- /.event-speakers-list -->
        </div>
    </section>
</c:if>

<!--========================================-->
<!--= Photos From Our Event =-->
<!--========================================-->
<c:if test="${not empty gallery}">
<section id="site-photos-from-our-event" class="site-photos-from-our-event section-padding">
    <div class="container">
        <div class="section-heading text-center">
            <h2 class="title">${gallery.pageTitle} <span class="effect"></span></h2>
        </div>
        <!-- /.section-heading -->

        <div class="dynamic-photo-gallery masonry-gallery" data-dynamic-gallery>
            <div class="photo-item sr-animated">
                <a href="uploads/${gallery.image1}" title="Image 01">
                    <img src="uploads/${gallery.image1}" alt="Image 01" width="350" height="300">
                </a>
            </div>
            <!-- /.photo-item -->
            <div class="photo-item sr-animated">
                <a href="uploads/${gallery.image2}" title="Image 02">
                    <img src="uploads/${gallery.image2}" alt="Image 02" width="354" height="300">
                </a>
            </div>
            <!-- /.photo-item -->
            <div class="photo-item sr-animated">
                <a href="uploads/${gallery.image3}" title="Image 03">
                    <img src="uploads/${gallery.image3}" alt="Image 03" width="350" height="300">
                </a>
            </div>
            <!-- /.photo-item -->
            <div class="photo-item sr-animated width-2">
                <a href="uploads/${gallery.image4}" title="Image 04">
                    <img src="uploads/${gallery.image4}" alt="Image 04" width="730" height="300">
                </a>
            </div>
            <!-- /.photo-item -->
            <div class="photo-item sr-animated">
                <a href="uploads/${gallery.image5}" title="Image 05">
                    <img src="uploads/${gallery.image5}" alt="Image 05" width="350" height="300">
                </a>
            </div>
            <!-- /.photo-item -->
        </div>
        <!-- /.dynamic-photo-gallery -->

    </div>
    <!-- /.container -->
</section>
</c:if>

<!--================================-->
<!--= Event Sponsor =-->
<!--================================-->
<c:if test="${not empty sponsors}">
<section id="site-event-sponsor" class="site-event-sponsor section-padding-two section-bgc-alt">
    <div class="container">
        <div class="section-heading text-center">
            <h2 class="title">${sponsors.pageTitle} <span class="effect"></span></h2>
        </div>
        <!-- /.section-heading -->

        <div class="event-sponsor-gallery">
            <div class="item">
                <a href="${sponsors.href1}">
                    <img src="uploads/${sponsors.image1}" alt="Sponsor 1" width="180" height="80">
                </a>
            </div>

            <div class="item">
                <a href="${sponsors.href2}">
                    <img src="uploads/${sponsors.image2}" alt="Sponsor 1" width="180" height="80">
                </a>
            </div>

            <div class="item">
                <a href="${sponsors.href3}">
                    <img src="uploads/${sponsors.image3}" alt="Sponsor 1" width="180" height="80">
                </a>
            </div>

            <div class="item">
                <a href="${sponsors.href4}">
                    <img src="uploads/${sponsors.image4}" alt="Sponsor 1" width="180" height="80">
                </a>
            </div>

            <div class="item">
                <a href="${sponsors.href5}">
                    <img src="uploads/${sponsors.image5}" alt="Sponsor 1" width="180" height="80">
                </a>
            </div>

            <div class="item">
                <a href="${sponsors.href6}">
                    <img src="uploads/${sponsors.image6}" alt="Sponsor 1" width="180" height="80">
                </a>
            </div>

        </div>
        <!-- /.event-sponsor-gallery -->
    </div>
    <!-- /.container -->
</section>
</c:if>

<!--==============================-->
<!--= Recent News =-->
<!--==============================-->
<c:if test="${not empty news}">
<section id="site-recent-news" class="site-recent-news section-padding-top">
    <div class="container">
        <div class="section-heading text-center">
            <h4 style="float: right"><a href="news/all">All News</a></h4>
            <h2 class="title">Recent News <span class="effect"></span></h2>
        </div>
        <div class="recent-news-list">
            <div class="row">
                <c:forEach items="${news}" var="item">
                    <div class="col-md-4 col-sm-6 sr-animated">
                        <article class="post">
                            <header class="post-header">
                                <div class="post-thumb">
                                    <a href="news/detail/${item.id}">
                                        <img style="height: 250px;
                                                background: url(uploads/${item.image});
                                                background-size: cover;
                                                background-position: center center;"
                                             width="370"
                                             height="250">
                                    </a>
                                </div>
                                <!-- /.post-thumb -->

                                <ul class="post-meta">
                                    <li>
                                        <i class="fa fa-calendar"></i>
                                        <a href="news/detail/${item.id}">
                                            <fmt:formatDate value="${item.dateCreated}" pattern="MM-dd-yyyy"/>
                                        </a>
                                    </li>
                                </ul>
                                <!-- /.post-meta -->

                                <h3 class="post-title">
                                    <a href="news/detail/${item.id}">
                                       ${item.title}
                                    </a>
                                </h3>
                            </header>
                            <!-- /.post-header -->

                           <%-- <div class="post-content">
                                <p>
                                   &lt;%&ndash;${fn:substring(item.description, 0, 143)}...&ndash;%&gt;
                                </p>
                            </div>--%>
                            <!-- /.post-content -->

                            <a href="news/detail/${item.id}" class="btn btn-devent btn-hs">Read More</a>
                        </article>
                    </div>
                </c:forEach>

            </div>
        </div>
        <hr>
    </div>
</section>
<!-- /#site-recent-news -->
</c:if>
<!--=============================-->
<!--= Contact Us =-->
<!--=============================-->
<section id="site-contact-us" class="site-contact-us section-padding">
    <div class="container">
        <div class="section-heading text-center">
            <h2 class="title">Contact Us <span class="effect"></span></h2>
        </div>
        <!-- /.section-heading -->

        <div class="row">
            <div class="col-md-8">
                <form action="contact/sendMail" method="post" class="contact-form sr-animated" data-deventform="contact">
                    <div class="row">
                        <div class="col-sm-6">
                            <textarea name="content" id="contact-content" cols="30" rows="15"
                                      placeholder="Your message ..." required></textarea>
                        </div>
                        <!-- /.col-sm-6 -->
                        <script src='https://www.google.com/recaptcha/api.js'></script>
                        <div class="col-sm-6">
                            <input type="text" name="name" id="contact-name" placeholder="Your Name ..." required>
                            <input type="email" name="email" id="contact-email" placeholder="Your Email ..." required>
                            <input type="text" name="subject" id="contact-subject" placeholder="Your Subject">
                            <div class="g-recaptcha" data-sitekey="6LfKWD4UAAAAAOOTfQQUjxgzd7IJla8Zqeak6OIx"></div>
                            <button type="submit" name="submit" id="contact-submit"
                                    class="btn btn-devent btn-hs btn-block">
                                <span>SUBMIT</span>
                                <i class="fa fa-circle-o-notch fa-spin"></i>
                            </button>
                        </div>
                        <!-- /.col-sm-6 -->

                        <div class="col-md-12">
                            <div class="form-result alert">
                                <div class="content"></div>
                            </div>
                            <!-- /.form-result-->
                        </div>
                    </div>
                    <!-- /.row -->
                </form>
            </div>
            <!-- /.col-md-8 -->

            <div class="col-md-4 sr-animated">
                <div class="map-display-area"
                     data-lat="41.724105"
                     data-lng="44.7050743"
                     data-mrkr="assets/img/map-marker.png">
                </div>
                <!-- /.map-display-area -->
            </div>
            <!-- /.col-md-4 -->
        </div>
        <!-- /.row -->
    </div>
    <!-- /.container -->
</section>
<!-- /#site-contact-us -->
