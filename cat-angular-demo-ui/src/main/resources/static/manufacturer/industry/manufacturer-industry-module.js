/**
 * Created by Mustafa on 06.08.2015.
 */
(function () {
    var app = angular.module('cat.angular.demo.manufacturer.industry.ManufacturerIndustry', []);

    function ManufacturerIndustry(data) {
        var that = this;
        _.extend(this, data);

        this.setParent = function (parent) {
            that.manufacturer = parent;
        }
    }

    app.config(['catRouteServiceProvider', function (catRouteServiceProvider) {
        catRouteServiceProvider
            .detailRoute('', 'Industry', {
                parent: 'Manufacturer.tab',
                name: 'ManufacturerIndustry',
                endpoint: {
                    parents: ['manufacturer'],
                    name: 'industry'
                }
            });
    }]);

    app.config(['catViewServiceProvider', function (catViewServiceProvider) {

        // register the models with the defaultModelResolver
        window.cat.models['ManufacturerIndustry'] = ManufacturerIndustry;
        // register CRUDL functionality
        catViewServiceProvider.listAndDetailView('', 'Industry');
    }]);
})();