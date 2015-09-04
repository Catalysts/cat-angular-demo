/**
 * CRUDL functionality for countries.
 *
 * @author Michael Mittermayr
 * @type {module}
 */
(function () {
    var app = angular.module('cat.angular.demo.country.CountryModule', [
        'cat.angular.demo.country.CountryController'
    ]);

    // Model
    function Country(data) {
        angular.extend(this, data);     //maps all the data properties
    }

    // Register CRUDL functionality + endpoint
    app.config(['catViewServiceProvider', function (catViewServiceProvider) {

        // We want to put our model in the list used by the defaultModelResolver.
        // The listAndDetailView function is going to use this model for the endpoint.
        //
        // Attention: In order to make this work correctly, the 'window.cat.util.defaultModelResolver' needs
        // to check this map for models. (Note: This is the default behaviour, but has been extended in 'demo.js')
        window.cat.models['Country'] = Country;

        // In here the actual magic happens, the endpoint is created, the states are registered.
        //
        // States:
        // - Country.list
        // - Country.details
        //
        // In addition to make things work, we need at least the following three template files:
        // - country-details-edit.tpl.html
        // - country-details-view.tpl.html
        // - country-list.tpl.html
        //
        // Again, the files have to match the required naming schema (replace country by your own endpoint's name).
        catViewServiceProvider.listAndDetailView('', 'Country', {
            url: 'countries'
        });
    }]);

    // In this sample we want to prevent users from deleting countries,
    // instead they should be able to disable such countries.
    //
    // Therefore we are going to decorate the catElementVisibilityService
    // since such decoration is global we need to find a way to avoid unwanted effects.
    // e.g. by checking the models "type"
    app.config(['$provide', function ($provide) {
        $provide.decorator('catElementVisibilityService', ['$delegate',
            function ($delegate) {

                // We obtain a copy of the underlying implementation of the isVisible function.
                // This would not be required if we decide to replace the default behaviour.
                // Since we want to provide a decorator like behaviour, we are going to prevent elements
                // form being shown, in cases where an underlying implementation would hide them.
                var baseFunction = $delegate.isVisible;

                $delegate.isVisible = function (identifier, data) {

                    // Since we registered our model with the defaultModelResolver we can simply check if it is an instanceof
                    if (identifier === 'cat.base.delete' && !!data.detail && data.detail instanceof Country) {
                        return false;
                    }

                    // We don't want to hide the element, lets ask the underlying implementation.
                    return baseFunction(identifier, data);
                };

                return $delegate;
            }]);
    }]);
})();