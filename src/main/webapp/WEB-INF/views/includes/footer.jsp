<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!--=========================-->
<!--= Footer =-->
<!--=========================-->
<footer id="site-footer" class="site-footer">
    <div class="footer-widget-area transparent-area" style="background-color: #2b2b2b !important;"
         <%--data-bg-image="http://handsometacos.com/wp-content/uploads/2016/10/bg.jpg"--%>
            <%/*http://www.xbee.com/wp-content/uploads/footer-background.png*/%>
         data-parallax="image">
        <div class="container">
            <div class="row">
                <div class="col-md-3 col-sm-6">
                    <aside id="widget_about_us" class="widget sr-animated widget_dt_about_us">
                        <h3 class="widget-title">About Us</h3>

                        <%--<p class="description">--%>
                            <%--Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor--%>
                            <%--invidunt.--%>
                        <%--</p>--%>

                        <ul class="contact-method">
                            <li>
                                <i class="fa fa-map-marker" aria-hidden="true"></i>
                                <span>${variable.results[0].value}</span>
                            </li>
                            <li>
                                <i class="fa fa-envelope-o" aria-hidden="true"></i>
                                <span>${variable.results[1].value}</span>
                            </li>
                            <li>
                                <i class="fa fa-phone" aria-hidden="true"></i>
                                <span>${variable.results[2].value}</span>
                            </li>
                        </ul>
                        <!-- /.contact-method -->

                        <ul class="social-profiles">
                            <li>
                                <a href="https://www.facebook.com/www.naec.ge/" target="_blank">
                                    <i class="fa fa-facebook" aria-hidden="true"></i>
                                    <span class="sr-only">Facebook</span>
                                </a>
                            </li>
                            <li>
                                <a href="https://twitter.com/naec_georgia" target="_blank">
                                    <i class="fa fa-twitter" aria-hidden="true"></i>
                                    <span class="sr-only">Twitter</span>
                                </a>
                            </li>
                            <li>
                                <a href="https://www.linkedin.com/company/naec/" target="_blank">
                                    <i class="fa fa-linkedin" aria-hidden="true"></i>
                                    <span class="sr-only">Linkedin</span>
                                </a>
                            </li>
                        </ul>

                    </aside>
                    <!-- /#widget_{ID} -->
                </div>
                <!-- /.col-md-3 -->
                <div class="col-md-3 col-sm-6">
                    <aside id="widget_recent_entries"
                           class="widget sr-animated widget_dt_recent_entries widget_recent_entries">
                        <h3 class="widget-title">Recent News</h3>

                        <ul>
                            <c:if test="${not empty lastnews}">
                                <c:forEach items="${lastnews}" var="item">
                                    <li><a href="#">${fn:substring(item.title, 0, 20)}...</a></li>
                                </c:forEach>
                            </c:if>
                        </ul>

                    </aside>
                    <!-- /#widget_{ID} -->
                </div>
                <!-- /.col-md-3 -->
                <%-- <div class="col-md-3 col-sm-6">
                     <aside id="widget_get_your_tickets" class="widget sr-animated widget_dt_get_your_tickets text-widget">
                         <h3 class="widget-title">Get your tickets</h3>

                         <div class="widget-content">
                             <p>Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore.</p>

                             <a href="#" class="btn btn-devent">Checkout Tickets!</a>
                         </div>
                         <!-- /.widget-content -->

                     </aside>
                     <!-- /#widget_{ID} -->
                 </div>--%>
                <!-- /.col-md-3 -->
                <%--<div class="col-md-3 col-sm-6">
                    <aside id="widget_photos_from_our_events" class="widget sr-animated widget_dt_photos_from_our_events text-widget">
                        <h3 class="widget-title">Photos Of Our Events</h3>

                        <div class="gallery-container" data-dynamic-gallery>
                            <ul>
                                <li>
                                    <a href="media/images/photos-from-our-event/full/widget/01.jpg" title="Image 01">
                                        <img src="media/images/photos-from-our-event/thumb/01.jpg" width="82" height="76" alt="Image 01">
                                    </a>
                                </li>
                                <li>
                                    <a href="media/images/photos-from-our-event/full/widget/02.jpg" title="Image 02">
                                        <img src="media/images/photos-from-our-event/thumb/02.jpg" width="82" height="76" alt="Image 02">
                                    </a>
                                </li>
                                <li>
                                    <a href="media/images/photos-from-our-event/full/widget/03.jpg" title="Image 03">
                                        <img src="media/images/photos-from-our-event/thumb/03.jpg" width="82" height="76" alt="Image 03">
                                    </a>
                                </li>
                                <li>
                                    <a href="media/images/photos-from-our-event/full/widget/04.jpg" title="Image 04">
                                        <img src="media/images/photos-from-our-event/thumb/04.jpg" width="82" height="76" alt="Image 04">
                                    </a>
                                </li>
                                <li>
                                    <a href="media/images/photos-from-our-event/full/widget/05.jpg" title="Image 05">
                                        <img src="media/images/photos-from-our-event/thumb/05.jpg" width="82" height="76" alt="Image 05">
                                    </a>
                                </li>
                                <li>
                                    <a href="media/images/photos-from-our-event/full/widget/06.jpg" title="Image 06">
                                        <img src="media/images/photos-from-our-event/thumb/06.jpg" width="82" height="76" alt="Image 06">
                                    </a>
                                </li>
                                <li>
                                    <a href="media/images/photos-from-our-event/full/widget/07.jpg" title="Image 07">
                                        <img src="media/images/photos-from-our-event/thumb/07.jpg" width="82" height="76" alt="Image 07">
                                    </a>
                                </li>
                                <li>
                                    <a href="media/images/photos-from-our-event/full/widget/08.jpg" title="Image 08">
                                        <img src="media/images/photos-from-our-event/thumb/08.jpg" width="82" height="76" alt="Image 08">
                                    </a>
                                </li>
                                <li>
                                    <a href="media/images/photos-from-our-event/full/widget/09.jpg" title="Image 09">
                                        <img src="media/images/photos-from-our-event/thumb/09.jpg" width="82" height="76" alt="Image 09">
                                    </a>
                                </li>
                            </ul>
                        </div>
                        <!-- /.gallery-container -->

                    </aside>
                    <!-- /#widget_{ID} -->
                </div>--%>
                <!-- /.col-md-3 -->
            </div>
            <!-- /.row -->
        </div>
        <!-- /.container -->
    </div>
    <!-- /.footer-widget-area -->

    <div class="copyright-info-area transparent-area">
        <div class="container">
            <p class="copyright-notice"><a href="#">Naec</a> - Copyright 2017. Designed by <a href="#">George
                Vashakidze</a></p>
        </div>
        <!-- /.container -->
    </div>
    <!-- /.copyright-info-area -->
</footer>
<!-- /#site-footer -->


</div>
<!-- /#site -->

<div id="back-to-top" class="btn btn-devent">
    <i class="fa fa-arrow-up" aria-hidden="true"></i>
</div>
<!-- /#back-to-top -->

<!-- Dependency Scripts -->
<script data-cfasync="false" src="/dependencies/jquery/jquery.min.js"></script>
<script data-cfasync="false" src="/dependencies/loaders.css/loaders.css.js"></script>
<script data-cfasync="false" src="/dependencies/bootstrap/js/bootstrap.min.js"></script>
<script data-cfasync="false" src="/dependencies/moment/moment.min.js"></script>
<script data-cfasync="false" src="/dependencies/moment-timezone/moment-timezone-with-data-2012-2022.min.js"></script>
<script data-cfasync="false" src="/dependencies/jquery.countdown/jquery.countdown.min.js"></script>
<script data-cfasync="false" src="/dependencies/Swiper/js/swiper.min.js"></script>
<script data-cfasync="false" src="/dependencies/scrollreveal/scrollreveal.js"></script>
<script data-cfasync="false" src="/dependencies/magnific-popup/jquery.magnific-popup.min.js"></script>
<script data-cfasync="false" src="//maps.googleapis.com/maps/api/js?key=AIzaSyAssaWBjq7xp_L88KSNA6X7wVA-HxF9rtM&region=US"></script>
<script data-cfasync="false" src="/dependencies/gmap3/gmap3.min.js"></script>

<!-- Site Scripts -->
<script src="/assets/js/app.js"></script>


<%--<div class="Switcher">
    <button id="Switcher__control" class="Switcher__control"><i class="fa fa-cog fa-spin fa-3x fa-fw"></i></button>
    <h5>Change Color</h5>
    <ul id="colors" data-dir="assets/css/themes/">
        <li data-scheme="default" data-color="#f2f2f2"></li>
        <li data-scheme="elegant" data-color="#B23256"></li>
        <li data-scheme="sky" data-color="#2EA1D9"></li>
        <li data-scheme="blackish" data-color="#303841"></li>
        <li data-scheme="dashing" data-color="#FA4848"></li>
        <li data-scheme="shango" data-color="#492540"></li>
        <li data-scheme="bhango" data-color="#083D56"></li>
        <li data-scheme="daku" data-color="#106EE8"></li>
        <li data-scheme="tinku" data-color="#FC5185"></li>
        <li data-scheme="jhanu" data-color="#020438"></li>
    </ul>
</div>
<script data-cfasync="false" src="/dependencies/colornip/colornip.min.js"></script>--%>
</body>

</html>