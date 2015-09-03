var app = angular.module('cat.demo.country.CountryModule', []);

// Model
function Country(data) {
    angular.extend(this, data);     //maps all the data properties
}

// Register CRUDL functionality + endpoint
// This also registers the states:
// * country.list
// * country.detail
app.config(function (catViewServiceProvider) {

    // We want to put our model in the list used by the defaultModelResolver.
    // The listAndDetailView function is going to use this model for the endpoint.
    window.cat.models['Country'] = Country;

    // In here the actual magic happens, the endpoint is created, the states are registered.
    catViewServiceProvider.listAndDetailView('', 'Country', {
        url: 'countries'
    });
});

// The listAndDetailView would work without a specific controller implementation.
// However, as we would like to extend the existing controller we need to create our own.
// Besides adding new functionality, we could also extend/change existing functions.
//
// IMPORTANT: The listAndDetailView finds the controllers by their name.
// - CountryController          -- list entries
// - CountryDetailsController   -- view/edit single entry
app.controller('CountryController', ['$scope', 'catListDataLoadingService', function ($scope, catListDataLoadingService) {

    function reloadList() {
        // We are going to use the list's endpoint and the used searchRequest.
        // Due to this we are able to keep filtering, sorting and paging as it is.
        catListDataLoadingService.load($scope.listData.endpoint, $scope.listData.searchRequest).then(
            function (data) {
                _.assign($scope.listData, data);
            }
        );
    }

    // Called form the template.
    // Update the current country and reload the list.
    $scope.toggleState = function () {
        var dataCopy = angular.copy(this.data);
        dataCopy.disabled = !dataCopy.disabled;

        $scope.listData.endpoint.save(dataCopy).then(reloadList);
    };

}]);

// In this sample we want to prevent users from deleting countries,
// instead they should be able to disable such countries.
//
// Therefore we are going to decorate the catElementVisibilityService
// since such decoration is global.
app.config(['$provide', function ($provide) {
    $provide.decorator('catElementVisibilityService', ['$delegate',
        function ($delegate) {

            // We obtain a copy of the underlying implementation of the isVisible function.
            // This would not be required if we decide to replace the default behaviour.
            // Since we want to provide a decorator like behaviour, we are going to prevent elements
            // form being shown, in cases where an underlying implementation has decided to hide them.
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