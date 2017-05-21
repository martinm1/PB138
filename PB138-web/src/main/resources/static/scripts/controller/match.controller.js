'use strict';

angular.module('sparta')
    .controller('MatchController', function ($stateParams, MatchService) {
        var vm = this;
        vm.matchId = $stateParams.matchId;
        vm.match = {};
        console.log("Match ID",vm.matchId);
        function init() {
            MatchService.findMatchById(vm.matchId).then(function (data) {
                vm.match = data;
                console.log(vm.match);
            });
        }

        init();
        return vm;
    });