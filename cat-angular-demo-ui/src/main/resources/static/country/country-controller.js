(function () {

    var app = angular.module('cat.angular.demo.country.CountryController', []);

    // The listAndDetailView would work without specific controller implementations
    // by using the existing generic implementation provided by cat-angular.
    //
    // However, as we would like to extend the existing list controller we need to create our own.
    // The controller will automatically extend from the generic implementation.
    //
    // NOTE: Besides adding new functionality, we could also extend/change existing functions.
    //
    // IMPORTANT: The listAndDetailView detects controllers by their name.
    // - CountryController          -- list entries
    // - CountryDetailsController   -- view/edit single entry
    //
    app.controller('CountryController', ['$scope', 'catListDataLoadingService', function ($scope, catListDataLoadingService) {

        function reloadList() {
            // We are going to use the list's endpoint and the used searchRequest.
            // Due to this we are able to keep filtering, sorting and paging as it is.
            catListDataLoadingService.load($scope.listData.endpoint, $scope.listData.searchRequest).then(
                function (data) {
                    _.assign($scope.listData, data);
                }
            );
        }

        // Called form the template.
        // Update the current country and reload the list.
        $scope.toggleState = function () {
            var dataCopy = angular.copy(this.data);
            dataCopy.disabled = !dataCopy.disabled;

            $scope.listData.endpoint.save(dataCopy).then(reloadList);
        };

    }]);
})();