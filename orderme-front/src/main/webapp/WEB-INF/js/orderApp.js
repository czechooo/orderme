var orderApp = angular.module("OrderApp",['angular-growl', 'ngAnimate']);

orderApp.config(['growlProvider', function (growlProvider) {
    growlProvider.globalTimeToLive(3000);
}]);

orderApp.controller('CreateOrderController', ['$scope', '$http',"$interval", 'growl',
    function ($scope, $http, $interval, growl) {
        $scope.order = {};

        var config = {};
        console.log('testo');
        growl.warning("testo");

        $scope.createOrder = function () {
            var dataToSend = {"text": $scope.order.text};
            console.log(dataToSend);
            $http({
                method: 'POST',
                url: createOrderUrl,
                data: dataToSend
            }).then(function successCallback(response) {
                console.log(response);
                $scope.orderSent = "Order Created";
                $scope.order = {};
                growl.success("Order has been placed", config);

            }, function errorCallback(response) {
                console.log(response);
                $scope.order = {};
                growl.error("Something went wrong");
            });
        };

        $scope.orders = [];
        var getOrders = function () {
            $http({
                method: 'GET',
                url: ordersUrl
            }).then(function successCallback(response) {
                console.log(response);
                $scope.orders = response.data;
            }, function errorCallback(response) {
                console.log(response);
            });
        };

        $scope.counter = 0;
        $scope.interval = 4;
        getOrders();
        var serverCallback = $interval(function () {

            $scope.counter++;

            if ($scope.counter % $scope.interval === 0) {
                $scope.counter = 0;
                getOrders();
            }

        }, 1000)
    }
]);