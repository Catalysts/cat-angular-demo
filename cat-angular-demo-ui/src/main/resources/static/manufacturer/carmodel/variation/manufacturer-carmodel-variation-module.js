/**
 * Created by Mustafa on 06.08.2015.
 */
angular.module('cat.angular.demo.manufacturer.carmodel.variation.ManufacturerCarmodelVariation', [])
    .config(function (catRouteServiceProvider) {
        catRouteServiceProvider
            .detailRoute('', 'Variation', {
                parent: 'Manufacturer.tab.CarModel.tab',
                name: 'ManufacturerCarModelVariation',
                endpoint: {
                    parents: ['manufacturer', 'carmodel'],
                    name: 'variation'
                }
            });
    });