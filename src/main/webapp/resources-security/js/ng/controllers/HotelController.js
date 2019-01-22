angular.module('app').controller('HotelController',
    ['$scope', '$http', 'GridManager', 'ModalManager','FileUploader', function ($scope, $http, GridManager, ModalManager, FileUploader) {

        angular.extend($scope, {
            url:'hotel/list',
            saveURL:'hotel/put',
            deleteURL:'hotel/delete',
            cityListURL: 'city/list?pageSize=-255',
            init:{},
            uploadPath:"/upload/files/",
            options:{
                height: 150,
                toolbar: [
                    ['edit',['undo','redo']],
                    ['headline', ['style']],
                    ['style', ['bold', 'italic', 'underline', 'superscript', 'subscript', 'strikethrough', 'clear']],
                    ['fontface', ['fontname']],
                    ['textsize', ['fontsize']],
                    ['fontclr', ['color']],
                    ['alignment', ['ul', 'ol', 'paragraph', 'lineheight']],
                    ['height', ['height']],
                    ['table', ['table']],
                    ['insert', ['link','picture','video','hr']],
                    ['view', ['fullscreen', 'codeview']],
                    ['help', ['help']]
                ]
            },
            starList:[
                {id:1,name:'1'},
                {id:2,name:'2'},
                {id:3,name:'3'},
                {id:4,name:'4'},
                {id:5,name:'5'}
            ],
            roomList:[
                {id:1,name:'1'},
                {id:2,name:'2'},
                {id:3,name:'3'},
                {id:4,name:'4'},
                {id:5,name:'5'},
                {id:6,name:'6'},
                {id:7,name:'7'},
                {id:8,name:'8'},
                {id:9,name:'9'},
                {id:10,name:'10'}
            ],
            adultList:[
                {id:1,name:'1'},
                {id:2,name:'2'},
                {id:3,name:'3'},
                {id:4,name:'4'},
                {id:5,name:'5'},
                {id:5,name:'6'},
                {id:5,name:'7'},
                {id:5,name:'8'}
            ],
            childList:[
                {id:1,name:'1'},
                {id:2,name:'2'},
                {id:3,name:'3'},
                {id:4,name:'4'},
                {id:5,name:'5'},
                {id:5,name:'6'},
                {id:5,name:'7'},
                {id:5,name:'8'}
            ],

        });

        GridManager.givePowerTo($scope);
        ModalManager.enableModals($scope);
        $scope.AmfTable.openPage(0);

        $scope.showAddEdit = function (item) {
            $scope.init.action = item ? 'რედაქტირება' : 'დამატება';
            $scope.object = {};
            $scope.object.gallery = [];
            if (item) {
                $scope.object = angular.copy(item);
            }
            $('#showAddEdit').modal('show');
        };

        $scope.save = function () {
            if($scope.uploader.queue.length > 0){
                hotelUploader.uploadAll();
            } else {
                if($scope.object.gallery === undefined || $scope.object.gallery == null || $scope.object.gallery.length == 0){
                    $scope.showErrorModal("Please upload image.");
                    return
                }
                $scope.sendSave();
            }
        };

        $scope.sendSave = function () {
            var objectCopy = angular.copy($scope.object);
            $http.post($scope.saveURL, objectCopy).success(function (response) {
                if (!response.success) {
                    return;
                }
                $scope.showSuccessAlert("Success");
                $scope.AmfTable.reloadData(true);
                $('#showAddEdit').modal('hide');
            });
        };
        
        $scope.loadCityList = function () {
            $http.get($scope.cityListURL).success(function (response) {
                $scope.city_list = response.results;
            })
        };

        $scope.loadCityList();

        $scope.delete = function (itemId) {
            $http.post($scope.deleteURL, itemId).
            success(function (data) {
                if (!data.success) {
                    if(response.errorMessage == "RECORD_IS_USED_IN_OTHER_TABLES"){
                        $scope.showErrorModal("მოცემული ჩანაწერის წაშლა შეუძლებელია რადგან ის ფიქსირდება სხვა ცხრილშიც.")
                    }
                    return;
                }
                $scope.AmfTable.reloadData(true);
            });
        };

        $scope.createFabMenuItems = function () {
            setTimeout(function () {
                var add = $scope.showAddEdit;
                var scope = angular.element($(".fabmenu")).scope();
                scope.$apply(function () {
                    scope.app.fabScope = $scope;
                    $scope.fabMenuItems = scope.app.fabMenuItems = [
                        {
                            callback: add,
                            "fa": "plus",
                            "title": "დამატება"
                        }
                    ];
                });
            }, 2000);
        };

        $scope.createFabMenuItems();


        // GOOGLE MAP
        var vm = $scope;
        vm.myMarkers = [];
        vm.mapOptions = {
            center: new google.maps.LatLng(35.784, -98.670),
            zoom: 4,
            mapTypeId: google.maps.MapTypeId.ROADMAP
        };

        vm.addMarker = function ($event,$params) {
            if(vm.myMarkers.length < 1){
                vm.myMarkers.push(new google.maps.Marker({
                    map: vm.myMaNp,
                    position: $params[0].latLng
                }));
            }
        };

        vm.setMarkerPosition = function(marker, lat, lng) {
            marker.setPosition(new google.maps.LatLng(lat, lng));
        };

        vm.clearMarkers  = function () {
            for(var k in vm.myMarkers){
                vm.myMarkers[k].setMap(null);
            }
            vm.myMarkers = [];
        };

        var hotelUploader = $scope.uploader = new FileUploader({
            url: 'upload/file'
        });

        // FILTERS
        hotelUploader.filters.push({
            name: 'imageFilter',
            fn: function(item /*{File|FileLikeObject}*/, options) {
                var type = '|' + item.type.slice(item.type.lastIndexOf('/') + 1) + '|';
                return '|jpg|png|jpeg|bmp|gif|'.indexOf(type) !== -1;
            }
        });

        // CALLBACKS
        hotelUploader.onBeforeUploadItem = function(item) {
            item.formData.push({type: 'HOTEL_IMG'});
        };

        hotelUploader.onCompleteItem = function(fileItem, response, status, headers) {
            if(response.success){
                if(response.source.length > 0){
                    $scope.object.gallery.push(response.source[0]);
                }
            }
        };

        hotelUploader.onCompleteAll = function() {
            // Clear File Input
            $("input[type='file']").val('').clone(true);
            $scope.sendSave();
        };

        $('#showAddEdit').on("hidden.bs.modal", function(){
            hotelUploader.queue = [];
            // Clear File Input
            $("input[type='file']").val('').clone(true);
        });


        var hotelGalleryUploader = $scope.secondUploader = new FileUploader({
            url: 'upload/file'
        });

        // CALLBACKS
        hotelGalleryUploader.onBeforeUploadItem = function(item) {
            item.formData.push({type: 'GENERAL'});
        };

        $scope.imageUploadEN = function(files, editor) {
            for(var i in files){
                hotelGalleryUploader.addToQueue(files[i]);
            }
            hotelGalleryUploader.uploadAll();
            hotelGalleryUploader.onCompleteItem = function(fileItem, response, status, headers) {
                if(response.success){
                    if(response.source.length > 0){
                        for(var file in response.source){
                            var fileName = response.source[file];
                            editor.insertImage($scope.editableEN, $scope.uploadPath+fileName, fileName);
                        }
                    }
                }
            };
        };

        $scope.imageUploadRU = function(files, editor) {
            for(var i in files){
                hotelGalleryUploader.addToQueue(files[i]);
            }
            hotelGalleryUploader.uploadAll();
            hotelGalleryUploader.onCompleteItem = function(fileItem, response, status, headers) {
                if(response.success){
                    if(response.source.length > 0){
                        for(var file in response.source){
                            var fileName = response.source[file];
                            editor.insertImage($scope.editableRU, $scope.uploadPath+fileName, fileName);
                        }
                    }
                }
            };
        };
    }]);
