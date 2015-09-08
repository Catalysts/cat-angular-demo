/**
 * Created by Mustafa on 06.08.2015.
 */
(function () {
    var app = angular.module('cat.angular.demo.manufacturer.carmodel.variation.ManufacturerCarmodelVariation', []);

    function ManufacturerCarModelVariation(data) {
        var that = this;
        _.extend(this, data);

        this.setParent = function (parent) {
            that.carModel = parent;
        }
    }

    app.config(['catRouteServiceProvider', function (catRouteServiceProvider) {
        catRouteServiceProvider
            .detailRoute('', 'Variation', {
                parent: 'Manufacturer.tab.CarModel.tab',
                name: 'ManufacturerCarModelVariation',
                endpoint: {
                    parents: ['manufacturer', 'carmodel'],
                    name: 'variation'
                }
            });
    }]);

    app.config(['catViewServiceProvider', function (catViewServiceProvider) {

        // register the models with the defaultModelResolver
        window.cat.models['ManufacturerCarModelVariation'] = ManufacturerCarModelVariation;
        // register CRUDL functionality
        catViewServiceProvider.listAndDetailView('', 'Variation');
    }]);
})();