angular.module('cat.angular.demo.manufacturer.carmodel.ManufacturerCarmodel', [])
.config(function (catRouteServiceProvider) {
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
    });