var app = angular.module('cat.angular.demo.samples.select.SelectModule', [
]);

// register the endpoint (countriesSelect)
app.config(function (catApiServiceProvider) {

    function Country(data) {
        angular.extend(this, data);     //maps all the data properties
    }

    // an endpoint, just for demonstration
    // we could also use the existing endpoint from Module:  'cat.demo.country.CountryModule'
    catApiServiceProvider.endpoint('countriesSelect', {
        url: 'countries',
        model: Country
    });
});


function SelectSamplesController($scope, catBreadcrumbsService) {

    catBreadcrumbsService.clear();

    catBreadcrumbsService.push({
        title: 'cat-select',
        key: 'cc.catalysts.samples.catselect'
    });

    var staticArray = [{id: 1, name: 'Wien', city: true}, {id: 2, name: 'Linz', city: true}, {
        id: 3,
        name: 'Hinterdupfing',
        city: false
    }];


    // Sample: Array -> Basic
    $scope.staticArrayBasic = staticArray;

    // Sample: Array -> Function

    $scope.staticFunction = function (queryParams) {
        return queryParams.success({
            elements: staticArray
        });

    };

    // Sample: Array -> Filtered
    $scope.staticArrayFiltered = {
        endpoint: staticArray,
        filter: function (item, index, array) {
            return item.city;
        }
    };

};

app.controller('SamplesController', ['$scope', 'catBreadcrumbsService', SelectSamplesController]);