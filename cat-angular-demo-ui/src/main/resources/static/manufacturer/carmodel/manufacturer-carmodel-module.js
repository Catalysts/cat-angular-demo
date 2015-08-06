angular.module('cat.angular.demo.manufacturer.carmodel.ManufacturerCarmodel', [])
.config(function (catRouteServiceProvider) {
            catRouteServiceProvider
                .detailRoute('', 'CarModel', {
                    parent: 'Manufacturer.tab',
                    name: 'ManufacturerCarModel',
                    endpoint: {
                        parents: ['manufacturer'],
                        name: 'carmodel'
                    }
                });
    });