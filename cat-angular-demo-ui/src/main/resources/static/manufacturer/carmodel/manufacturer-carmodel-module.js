(function () {
    var app = angular.module('cat.angular.demo.manufacturer.carmodel.ManufacturerCarmodel', []);

    function ManufacturerCarModel(data) {
        var that = this;
        _.extend(this, data);

        this.setParent = function (parent) {
            that.manufacturer = parent;
        }
    }

    app.config(['catViewServiceProvider', function (catViewServiceProvider) {

        // register the models with the defaultModelResolver
        window.cat.models['ManufacturerCarModel'] = ManufacturerCarModel;
        // register CRUDL functionality
        catViewServiceProvider.listAndDetailView('', 'CarModel');

    }]);

    app.config(['catRouteServiceProvider', function (catRouteServiceProvider) {
        catRouteServiceProvider
            .detailRoute('', 'CarModel', {
                parent: 'Manufacturer.tab',
                name: 'ManufacturerCarModel',
                endpoint: {
                    parents: ['manufacturer'],
                    name: 'carmodel'
                },
                additionalViewTemplate: 'tabs',
                additionalViewTemplateTabs: [
                    {
                        name: 'variation',
                        icon: 'gift',
                        // TODO remove as soon as default controller correctly resolves parent relationships
                        controller: 'ManufacturerCarmodelVariationController'
                    }
                ]
            });
    }]);
})();