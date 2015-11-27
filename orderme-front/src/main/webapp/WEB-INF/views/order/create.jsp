<%@ taglib prefix="spring"   uri="http://www.springframework.org/tags" %>

<script>
    var createOrderUrl = "<spring:url value="/order/create" />";
    var ordersUrl = "<spring:url value="/order/list" />";
</script>

<div ng-app="OrderApp" ng-controller="CreateOrderController">
    <div class="row create-order" >
        <div class="col-lg-offset-3 col-lg-6 text-center">

            <h2>Create Order</h2>

            <div class="row create-order__row">

                <div class="col-lg-12">
                    <textarea class="create-order__order-textarea"
                              ng-model="order.text">
                    </textarea>
                </div>

            </div>

            <div class="row">
                <div class="col-lg-offset-6 col-lg-3">
                    <div ng-bind="orderSent" class="pull-right create-order__message"></div>
                </div>
                <div class="col-lg-3">
                    
                    <a class="btn btn-primary pull-right create-order__create-button" ng-click="createOrder()">Create</a>
                </div>
            </div>

        </div>
    </div>

    <div class="row order-list text-center">
        <div class="col-lg-offset-3 col-lg-6">
            <div>
                <h3>Order List</h3>

                <span ng-show="counter !== 0">
                    Referesh in <span ng-bind="interval - counter"></span>s
                </span>
                <span ng-show="counter === 0" >
                    Just Refreshed!
                </span>

            </div>

            <div class="order-list__order clearfix" ng-repeat="order in orders">
                <div class="order-list__order-item" ng-repeat="item in order.items">
                    Item <span ng-bind="$index+1"></span>: <span ng-bind="item.text"></span>
                </div>
            </div>

        </div>
    </div>

</div>