<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<section id="site-event-speakers" class="site-event-speakers section-padding" data-carousel="swiper">
    <div class="container speakers-profile-carousel">
        <!-- Speaker Profile -->
        <div class="event-speaker-profile swiper-container" style="padding-top: 60px;padding-bottom: 60px;">
            <div class="swiper-wrapper">
                <div class="swiper-slide">
                    <div class="speaker-profile-details" style="opacity: 1 !important; align-items: unset !important;">
                        <div class="profile-image" data-animate="fadeInUp" data-delay="0.2s" data-duration="0.5s">
                            <img src="/uploads/${data.imageURL}" width="480" height="460">
                        </div>
                        <!-- /.profile-image -->

                        <div class="profile-content">
                            <div class="name-job" data-animate="fadeInUp" data-delay="0.4s" data-duration="0.5s">
                                <h3 class="name">${data.title}</h3>
                                <div class="job" style="display: none;">
                                    <b>Created:</b> <fmt:formatDate value="${data.dateCreated}" pattern="MM-dd-yyyy"/>
                                </div>
                            </div>
                            <!-- /.name-job -->

                            <div class="biography" data-animate="fadeInUp" data-delay="0.6s" data-duration="0.5s">
                                ${data.descr}

                                    <c:if test="${fn:length(data.array) > 0}">
                                        <p style="margin-top: 10px; border-top: solid 1px #e5e5e5;">
                                            <span>Attached Files</span><br />
                                            <c:forEach items="${data.array}" var="element" varStatus="loop">
                                                <a href="/uploads/${element}"
                                                   target="_blank">${element}</a><br/>
                                            </c:forEach>
                                        </p>
                                    </c:if>
                            </div>
                            <!-- /.biography -->

                            <!-- /.social-profiles -->
                        </div>
                        <!-- /.profile-content -->
                    </div>

                    <c:if test="${not empty data.videoURL}">
                        <iframe width="100%" height="460" src="https://www.youtube.com/embed/${data.videoURL}"
                                frameborder="0"
                                gesture="media"
                                allow="encrypted-media"
                                allowfullscreen></iframe>
                    </c:if>
                </div>
                <!-- /.swiper-slide -->

            </div>
            <!-- /.swiper-wrapper -->
        </div>
        <!-- /.event-speaker-profile -->
    </div>
    <!-- /.container -->
</section>
