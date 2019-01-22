/*!
 * This beautiful code written with heart
 * by Aminul Islam <aminul@decentthemes.com>
 * In Nawabganj, BD at the Decent Themes workstation.
 */
var DECENTTHEMES = DECENTTHEMES || {};

(function($) {

  // USE STRICT
  "use strict";

  DECENTTHEMES.initialize = {

    init: function() {
      DECENTTHEMES.initialize.general();
      DECENTTHEMES.initialize.backToTop();
      DECENTTHEMES.initialize.eventsCountdown();
      DECENTTHEMES.initialize.stickyHeader();
      DECENTTHEMES.initialize.mobileMenu();
      DECENTTHEMES.initialize.navigationMenu();
      DECENTTHEMES.initialize.sectionBackground();
      DECENTTHEMES.initialize.sectionSize();
      DECENTTHEMES.initialize.swiperSlider();
      DECENTTHEMES.initialize.magnificPopup();
      DECENTTHEMES.initialize.gmap3();
      DECENTTHEMES.initialize.contactForm();
    },

    /*-----------------------------------------------
     # Collection of snippet and tweaks
     ---------------------------------------------*/
    general: function() {
      $(window).load(function() {
        $("#site-preloader").delay(350).fadeOut("slow");

        // Run ScrollReavel
        if (typeof ScrollReveal == 'function') {
          window.sr = ScrollReveal();
          sr.reveal('.sr-animated', {
            duration: 1000,
            viewOffset: {
              top: 150
            },
            mobile: false
          });
        }

      });

      // Toggle Handler
      $('[data-dt-toggle]').each(function() {
        var $this = $(this),
          $class = $this.data('dt-toggle'),
          $altClass = $this.data('dt-toggle-false');

        $this.on('click', function(e) {
          e.preventDefault();
          $this.toggleClass('active');
          $('body').toggleClass($class).removeClass($altClass);
        })
      })
    },

    backToTop: function() {
      var scrollTrigger = window.innerHeight, // px
        backToTop = function() {
          var scrollTop = $(window).scrollTop();
          if (scrollTop > scrollTrigger) {
            $('#back-to-top').addClass('visible');
          } else {
            $('#back-to-top').removeClass('visible');
          }
        };

      backToTop();
      $(window).on('scroll', function() {
        backToTop();
      });

      $('#back-to-top').on('click', function() {
        $('html,body').animate({
          scrollTop: 0
        }, 700);
      });
    },

    /*-----------------------------------------------
     # Event countdown handler
     ---------------------------------------------*/
    eventsCountdown: function() {
      if ($().countdown) {
        $('.event-countdown').each(function() {
          if (
            typeof moment == 'function' &&
            typeof moment.tz == 'function'
          ) {

            // Variables
            var $this = $(this),
              $countdown = $('.countdown', $this),
              $startDate = ($this.data('start')) ? $this.data('start') : '2020-01-01 00:00',
              $endDate = ($this.data('end')) ? $this.data('end') : '2020-01-01 00:00',
              $timezone = ($this.data('timezone')) ? $this.data('timezone') : 'Asia/Dhaka',
              $tDay = ($this.data('text-day')) ? $this.data('text-day') : 'Day',
              $tDays = ($this.data('text-days')) ? $this.data('text-days') : 'Days',
              $tHour = ($this.data('text-hour')) ? $this.data('text-hour') : 'Hour',
              $tHours = ($this.data('text-hours')) ? $this.data('text-hours') : 'Hours',
              $tMinute = ($this.data('text-minute')) ? $this.data('text-minute') : 'Minute',
              $tMinutes = ($this.data('text-minutes')) ? $this.data('text-minutes') : 'Minutes',
              $tSecond = ($this.data('text-second')) ? $this.data('text-second') : 'Second',
              $tSeconds = ($this.data('text-seconds')) ? $this.data('text-seconds') : 'Seconds',
              $eventSD = moment.tz($startDate, $timezone).toDate(),
              $eventED = moment.tz($endDate, $timezone).toDate();

            // Start countdown
            $countdown.countdown($eventSD, function(event) {

              // Countdown format
              var format = '' +
                '<div class="item">' +
                '<span class="text">' +
                '%!D:' + $tDay + ',' + $tDays + ';' +
                '</span>' +
                '<span class="number">' +
                '%D' +
                '</span>' +
                '</div>' +
                '<div class="item">' +
                '<span class="text">' +
                '%!H:' + $tHour + ',' + $tHours + ';' +
                '</span>' +
                '<span class="number">' +
                '%H' +
                '</span>' +
                '</div>' +
                '<div class="item">' +
                '<span class="text">' +
                '%!M:' + $tMinute + ',' + $tMinutes + ';' +
                '</span>' +
                '<span class="number">' +
                '%M' +
                '</span>' +
                '</div>' +
                '<div class="item">' +
                '<span class="text">' +
                '%!S:' + $tSecond + ',' + $tSeconds + ';' +
                '</span>' +
                '<span class="number">' +
                '%S' +
                '</span>' +
                '</div>';

              // Print to the selected element
              $(this).html(event.strftime(format));
            });
          }

        });
      }
    },

    /*-----------------------------------------------
     # Sticky Navigation
     ---------------------------------------------*/
    stickyHeader: function() {
      var $mainHeader = $('#site-header'),
        $topAreaH = $('#site-header .header-top-area').outerHeight(),
        $topBarH = $('#site-header .header-top-bar').outerHeight(),
        $maskHeader = $('#header-fake-mask'),
        $siteHeaderH = $mainHeader.outerHeight(),
        $totalTopH = ($topAreaH + $topBarH);

      // Add faker height
      $maskHeader.css('height', $siteHeaderH + 'px');

      //set scrolling variables
      var scrolling = false,
        previousTop = 0,
        currentTop = 0,
        scrollDelta = 10,
        scrollOffset = 150;

      $(window).on('scroll', function() {
        if (!scrolling) {
          scrolling = true;
          (!window.requestAnimationFrame) ?
          setTimeout(autoHideHeader, 250): requestAnimationFrame(autoHideHeader);
        }
      });

      function autoHideHeader() {
        var currentTop = $(window).scrollTop();

        checkStickyNavigation(currentTop);

        previousTop = currentTop;
        scrolling = false;
      }

      function checkStickyNavigation(currentTop) {
        if (previousTop >= currentTop) {
          if (currentTop < $siteHeaderH) {
            $('body').removeClass('fix-header is-scrolled');
          } else if (previousTop - currentTop > scrollDelta) {
            $('body').addClass('is-scrolled');
            $mainHeader.css('top', '0px');
          }

        } else {
          //if scrolling down...
          if (currentTop > $siteHeaderH + scrollOffset) {
            $('body').addClass('fix-header').removeClass('is-scrolled');
            $mainHeader.css('top', '-' + $totalTopH + 'px');
          } else if (currentTop > $siteHeaderH) {
            $('body').removeClass('is-scrolled');
          }

        }
      }
    },

    /*-----------------------------------------------
     # Navigation menu
     ---------------------------------------------*/
    navigationMenu: function() {

      // Handle the drop downs
      $('.menu-item-has-children').each(function() {
        var $this = $(this);

        // Add class when mouse enter
        $this.on('mouseenter', '> a', function(event) {
          $this.addClass('children-menu-visible');
        });

        // Not click able link when it has "#"
        $this.on('click', '> a', function(e) {
          if (($(this).attr('href').substr(0, 1)) == '#') {
            event.preventDefault();
          }
        });

        // Remove class when mouse leave
        $this.on('mouseleave', function() {
          $this.removeClass('children-menu-visible');
        });

      });

      // Manage one-page scrolling
      $('[data-type="section-switch"], .dt-standard-navbar a').on('click', function() {
        if (location.pathname.replace(/^\//, '') == this.pathname.replace(/^\//, '') && location.hostname == this.hostname) {
          var target = $(this.hash);
          if (target.length > 0) {

            target = target.length ? target : $('[name=' + this.hash.slice(1) + ']');
            $('html,body').animate({
              scrollTop: target.offset().top
            }, 1000);
            return false;
          }
        }
      });
    },

    /*-----------------------------------------------
     # Mobile Menu
     ---------------------------------------------*/

    mobileMenu: function() {
      if ($('body').hasClass('fix-header') && $(window).innerWidth() < 768) {
        $('#dt-primary-navigation.in').css({
          height: ($(window).innerHeight() - 35) + 'px',
          overflowY: 'scroll'
        });
      }
    },

    /*-----------------------------------------------
     # Section Background
     ---------------------------------------------*/
    sectionBackground: function() {
      // Section Background Color
      $('[data-bg-color]').each(function() {
        var value = $(this).data('bg-color');
        $(this).css({
          backgroundColor: value,
        });
      });

      // Section Background Image
      $('[data-bg-image]').each(function() {
        var img = $(this).data('bg-image');
        $(this).css({
          backgroundImage: 'url(' + img + ')',
        });
      });
    },

    /*-----------------------------------------------
     # Section Background Parallax
     ---------------------------------------------*/
    sectionBackgroundParallax: function() {
      $('[data-parallax="image"]').each(function() {

        var actualHeight = $(this).position().top;
        var offset = $(this).data('parallax-offset');
        var reSize = actualHeight - $(window).scrollTop();
        var makeParallax = -(reSize / 2);
        var posValue = makeParallax + "px";

        // Set background Image postion
        $(this).css({
          backgroundPosition: '50% ' + posValue,
        });

      });
    },

    /*-----------------------------------------------
     # Section Sizing
     ---------------------------------------------*/
    sectionSize: function() {
      var $screenH = self.innerHeight,
        $screenW = self.innerWidth,
        $headerH = $('.site-header').outerHeight(),
        $bannerH = $screenH - $headerH;

      // Make fullscreen section
      $('.full-screen').each(function() {
        $(this).css('height', $screenH + 'px')
      });

      // Banner Full screen
      if ($bannerH > 500) {
        if ($('body').hasClass('transparent-header')) {
          $('.site-banner > .banner-overlay').css({
            height: $screenH + 'px',
            paddingTop: $headerH + 'px',
          });
          $('.site-banner .swiper-slide > .middle-content').css({
            height: $screenH + 'px',
            paddingTop: $headerH + 'px',
          });
        } else {
          $('.site-banner').css('height', $bannerH + 'px');
          $('.site-banner > .banner-overlay').css('height', $bannerH + 'px');
        }
      }
    },

    /*-----------------------------------------------
     # Swiper Slider
     ---------------------------------------------*/
    swiperSlider: function() {
      $('[data-carousel="swiper"]').each(function() {

        var $this = $(this),
          $container = $this.find('[data-swiper="container"]'),
          $asControl = $this.find('[data-swiper="ascontrol"]');

        // Configuration
        var conf = function(element) {
          var obj = {
            slidesPerView: element.data('items'),
            centeredSlides: element.data('center'),
            loop: element.data('loop'),
            initialSlide: element.data('initial'),
            effect: element.data('effect'),
            spaceBetween: element.data('space'),
            autoplay: element.data('autoplay'),
            autoHeight: element.data('auto-height'),
            direction: element.data('direction'),
            paginationClickable: true,
            breakpoints: element.data('breakpoints'),
            slideToClickedSlide: element.data('click-to-slide'),
            loopedSlides: element.data('looped'),
            fade: {
              crossFade: element.data('crossfade')
            },
            paginationBulletRender: function(swiper, index, className) {
              return '<li class="' + className + '"><span class="count">0' + (index + 1) + '</span></li>';
            }
          };
          return obj;
        }

        // Primary Configuration
        var $primaryConf = conf($container);
        // Pagination and Nav Settings
        $primaryConf.prevButton = $this.find('[data-swiper="prev"]');
        $primaryConf.nextButton = $this.find('[data-swiper="next"]');
        $primaryConf.pagination = $this.find('[data-swiper="pagination"]');

        // As Control Configuration\
        var $ctrlConf = conf($asControl);

        // Animate Function
        function animateSwiper(selector, slider) {
          var makeAnimated = function animated() {
            selector.find('.swiper-slide-active [data-animate]').each(function() {
              var anim = $(this).data('animate'),
                delay = $(this).data('delay'),
                duration = $(this).data('duration');

              $(this).addClass(anim + ' animated')
                .css({
                  webkitAnimationDelay: delay,
                  animationDelay: delay,
                  webkitAnimationDuration: duration,
                  animationDuration: duration
                })
                .one('webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend', function() {
                  $(this).removeClass(anim + ' animated');
                });
            });
          };
          makeAnimated();
          // Make animated when slide change
          slider.on('SlideChangeStart', function() {
            selector.find('[data-animate]').each(function() {
              var anim = $(this).data('animate');
              $(this).removeClass(anim + ' animated');
            });
          });
          slider.on('SlideChangeEnd', makeAnimated);
        };

        if ($container.length) {
          // Run Swiper
          var $swiper = new Swiper($container, $primaryConf);
          // Make Animated Layer
          animateSwiper($this, $swiper);

          if ($asControl.length) {
            var $control = new Swiper($asControl, $ctrlConf);
            $swiper.params.control = $control;
            $control.params.control = $swiper;
          }

        } else {
          console.log('Swiper container is not defined!');
        };

      });
    },

    /*-----------------------------------------------
     # Magnific Pop
     ---------------------------------------------*/
    magnificPopup: function() {
      // Dynamic gallery
      $('[data-dynamic-gallery]').each(function() {
        $(this).magnificPopup({
          delegate: 'a',
          type: 'image',
          closeOnContentClick: false,
          closeBtnInside: false,
          mainClass: 'mfp-with-zoom mfp-img-mobile',
          image: {
            verticalFit: true,
            titleSrc: function(item) {
              return item.el.attr('title');
            }
          },
          gallery: {
            enabled: true
          },
          zoom: {
            enabled: true,
            duration: 300, // don't foget to change the duration also in CSS
            opener: function(element) {
              return element.find('img');
            }
          }
        });
      });

      // For video, map or iframe
      $('[data-lighbox-iframe]').each(function() {
        $(this).magnificPopup({
          disableOn: 700,
          type: 'iframe',
          mainClass: 'mfp-fade',
          removalDelay: 160,
          preloader: false,

          fixedContentPos: false
        });
      });
    },

    /*-----------------------------------------------
     # Google Map by GMAP3
     ---------------------------------------------*/
    gmap3: function() {
      $('.map-display-area').each(function() {
        var $this = $(this),
          key = $this.data('key'),
          lat = $this.data('lat'),
          lng = $this.data('lng'),
          mrkr = $this.data('mrkr');

        $this.gmap3({
            center: [lat, lng],
            zoom: 16,
            scrollwheel: false,
            mapTypeId: google.maps.MapTypeId.ROADMAP
          })
          .marker(function(map) {
            return {
              position: map.getCenter(),
              icon: mrkr
            };
          })

      });
    },

    contactForm: function() {
      $('[data-deventform]').each(function() {
        var $this = $(this);
        $('.form-result', $this).css('display', 'none');

        $this.submit(function() {

          $('button[type="submit"]', $this).addClass('clicked');

          // Create a object and assign all fields name and value.
          var values = {};

          $('[name]', $this).each(function() {
            var $this = $(this),
              $name = $this.attr('name'),
              $value = $this.val();
            values[$name] = $value;
          });

          // Make Request
          $.ajax({
            url: $this.attr('action'),
            type: 'POST',
            data: values,
            success: function success(data) {
              if (data.error == true) {
                $('.form-result', $this).addClass('alert-warning').removeClass('alert-success alert-danger').css('display', 'block');
              } else {
                $('.form-result', $this).addClass('alert-success').removeClass('alert-warning alert-danger').css('display', 'block');
              }
              $('.form-result > .content', $this).html(data.message);
              $('button[type="submit"]', $this).removeClass('clicked');
            },
            error: function error() {
              $('.form-result', $this).addClass('alert-danger').removeClass('alert-warning alert-success').css('display', 'block');
              $('.form-result > .content', $this).html('Sorry, an error occurred.');
              $('button[type="submit"]', $this).removeClass('clicked');
            }
          });
          return false;
        });

      });
    },
  };

  DECENTTHEMES.documentOnReady = {
    init: function() {
      DECENTTHEMES.initialize.init();
    },

  };

  DECENTTHEMES.documentOnLoad = {
    init: function() {
      DECENTTHEMES.initialize.stickyHeader();
    },

  };

  DECENTTHEMES.documentOnResize = {
    init: function() {
      DECENTTHEMES.initialize.sectionSize();
      DECENTTHEMES.initialize.stickyHeader();
      DECENTTHEMES.initialize.mobileMenu();
    },

  };

  DECENTTHEMES.documentOnScroll = {
    init: function() {
      DECENTTHEMES.initialize.sectionBackgroundParallax();
      DECENTTHEMES.initialize.mobileMenu();
    },

  };

  // Initialize Functions
  $(document).ready(DECENTTHEMES.documentOnReady.init);
  $(window).on('load', DECENTTHEMES.documentOnLoad.init);
  $(window).on('resize', DECENTTHEMES.documentOnResize.init);
  $(window).on('scroll', DECENTTHEMES.documentOnScroll.init);

})(jQuery);