(function () {
    var app = angular.module('cat.angular.demo.samples.select.SelectModule', []);

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

    // countriesConfigProvider
    app.config(function (catSelectConfigServiceProvider) {

        // We are going to register the named config.
        // By doing so, we are able to use the same config several times.
        catSelectConfigServiceProvider.config('samplesConfig', {
            endpoint: 'countriesSelect',
            'ui-select2': {     // include some ui-select2 specific settings
                allowClear: true
            }
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
        // Array as endpoint.
        $scope.staticArrayBasic = staticArray;

        // Sample: Array -> Function
        // Function as endpoint.
        $scope.staticFunction = function (queryParams) {
            return queryParams.success({
                elements: staticArray
            });
        };

        // Sample: Array -> Filtered
        // Setting a custom filter function.
        $scope.staticArrayFiltered = {
            endpoint: staticArray,
            filter: function (item, index, array) {
                return item.city;
            }
        };

        // Sample: Custom formatting function
        $scope.formatFunction = {
            endpoint: staticArray,
            'ui-select2': {
                // the text used for the select list items
                formatResult: function (obj) {
                    return obj.name + (obj.city ? ' (City)' : ' (Town)');
                },
                // the text used for the selected item
                formatSelection: function (obj) {
                    return obj.name;
                }
            }
        };

    }

    app.controller('SamplesController', ['$scope', 'catBreadcrumbsService', SelectSamplesController]);
})();