angular.module('app').directive('angularYoutube', function ($sce) {
    return {
        restrict: 'E',
        scope: { video: '=' },
        replace: true,
        template: '<div style="height:300px;"><iframe style="border: 0;overflow:hidden;height:100%;width:100%" width="100%" height="100%" src="{{url}}" frameborder="1" allowfullscreen></iframe></div>',
        link: function (scope) {
            scope.$watch('video', function (newVal) {
                if (newVal) {
                    scope.url = $sce.trustAsResourceUrl("http://www.youtube.com/embed/" + newVal);
                }
            });
        }
    }
});