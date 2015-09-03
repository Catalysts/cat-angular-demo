var app = angular.module('cat.demo.manufacturer.ManufacturerDetailsController', [
    'cat.angular.demo.manufacturer.carmodel.ManufacturerCarmodelController',
    'cat.angular.demo.manufacturer.industry.ManufacturerIndustryController',
    'cat.demo.country.CountryModule'
]);

app.controller('ManufacturerDetailsController', ['$scope', 'catApiService', function ($scope, catApiService) {
    $scope.foundingDateModel = function (value) {
        if (angular.isDefined(value)) {
            $scope.editDetail.foundingDate = value.toISOString().substring(0, 10);
        }

        return $scope.editDetail.foundingDate;
    };

    // Config for the cat-select
    $scope.countriesConfig = {
        // use the endpoint, registered in country/country-module.js
        endpoint: catApiService.country,

        // [OPTIONAL] searchRequestAdpater: apply changes to the searchRequest prior to search execution
        //searchRequestAdpater: function (searchRequest) {
        //    return searchRequest;
        //},
        //
        // [OPTIONAL] search: build a custom searchRequest
        //search: function (term, page) {
        //    return {
        //        'search.name': term,
        //        page: page
        //    };
        //},

        filter: function (item, index, dataArray) {
            // OPTIONAL: additional filtering done on the client side
            return !item.disabled;
        }
    };
}]);