<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="HtmlTruncator" uri="/WEB-INF/tlds/HtmlTruncator" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="pagination" uri="/WEB-INF/tlds/Pagination" %>
<jsp:useBean id="dateValue" class="java.util.Date"/>
<div class="container">
    <div class="row section-padding-top">
        <div class="col-md-8">
            <c:if test="${not empty list.results}">
                <section id="site-blog-entries" class="site-blog-entries">

                    <c:forEach items="${list.results}" var="item">
                        <article class="devent_post_thumbnail_enabled
                                post type-post status-publish
                                has-post-thumbnail format-standard hentry sr-animated">
                            <div class="post-thumbnail">
                                <a href="/news/detail/${item.id}">
                                    <img style="height: 250px;
                                            background: url(/uploads/${item.image});
                                            background-size: cover;
                                            background-position: center center;">
                                </a>
                            </div>
                            <div class="post-content">
                                <h2 class="entry-title">
                                    <a href="/news/detail/${item.id}">
                                            ${fn:substring(item.title, 0, 50)}...
                                    </a>
                                </h2>

                                <ul class="entry-meta">
                                    <li>
                                        <i class="fa fa-calendar-o"></i>
                                        <a href="/news/detail/${item.id}">
                                            <fmt:formatDate value="${item.dateCreated}" pattern="MM-dd-yyyy"/>
                                        </a>
                                    </li>
                                    <li>
                                        <i class="fa fa-share-alt"></i>
                                        <a href="/news/detail/${item.id}">Share</a>
                                    </li>
                                </ul>
                                <!-- /.entry-meta -->

                                <div class="entry-excerpt">
                                    <p>
                                            ${fn:substring(item.description, 0, 240)}...
                                    </p>
                                </div>
                                <!-- /.entry-excerpt -->

                                <a href="/news/detail/${item.id}" class="btn btn-devent btn-hs readmore">Read More</a>
                            </div>
                        </article>
                    </c:forEach>
                    <nav class="navigation pagination" role="navigation">
                        <div class="nav-links">
                            <c:if test="${not empty list.results}">
                                <%--<a class="prev page-numbers" href="#"><span class="sr-only">Previous page</span></a>--%>
                                <pagination:display
                                        uri="/news/all?searchExpression=${empty param.searchExpression? '':param.searchExpression}"
                                        currPage="${ empty param.pageNumber ? 0:param.pageNumber}"
                                        maxPage="${data.maxPages}"
                                        totalItems="${data.total}"
                                        pageSize="10"/>
                                <%--<a class="next page-numbers" href="#"><span class="sr-only">Next page</span></a>--%>
                            </c:if>
                        </div>
                    </nav>
                </section>
            </c:if>
        </div>
        <div class="col-md-4">
            <section id="site-sidebar" class="site-sidebar">
                <aside id="widget_search" class="widget sr-animated widget_dt_search widget_search">
                    <h3 class="widget-title">Search</h3>
                    <form role="search" method="get" class="search-form">
                        <span class="sr-only">Search for:</span>
                        <input type="text" name="searchExpression" value="${searchExpression}" class="form-control"
                               placeholder="Type keywords ...">
                        <input type="hidden" name="currPage" value="0">
                        <button type="submit"><i class="fa fa-search"></i> <span class="sr-only">Submit</span></button>
                    </form>
                </aside>
                <!-- /#widget_{ID} -->
                <aside id="widget_about_us" class="widget sr-animated widget_dt_about_us">
                    <h3 class="widget-title">About Us</h3>
<%--

                    <p class="description">
                        Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt.
                    </p>
--%>

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
                <c:if test="${not empty gallery}">
                    <aside id="widget_photos_from_our_events"
                           class="widget sr-animated widget_dt_photos_from_our_events text-widget">
                        <h3 class="widget-title">Gallery</h3>
                        <div class="gallery-container" data-dynamic-gallery>
                            <ul>
                                <li>
                                    <a href="/uploads/${gallery.image1}" title="Image 01">
                                        <img src="/uploads/${gallery.image1}" width="82" height="76">
                                    </a>
                                </li>
                                <li>
                                    <a href="/uploads/${gallery.image2}" title="Image 01">
                                        <img src="/uploads/${gallery.image2}" width="82" height="76">
                                    </a>
                                </li>
                                <li>
                                    <a href="/uploads/${gallery.image3}" title="Image 01">
                                        <img src="/uploads/${gallery.image3}" width="82" height="76">
                                    </a>
                                </li>
                                <li>
                                    <a href="/uploads/${gallery.image4}" title="Image 01">
                                        <img src="/uploads/${gallery.image4}" width="82" height="76">
                                    </a>
                                </li>
                                <li>
                                    <a href="/uploads/${gallery.image5}" title="Image 01">
                                        <img src="/uploads/${gallery.image5}" width="82" height="76">
                                    </a>
                                </li>
                            </ul>
                        </div>
                        <!-- /.gallery-container -->
                    </aside>
                </c:if>
                <!-- /#widget_{ID} -->
            </section>
        </div>
    </div>
</div>
