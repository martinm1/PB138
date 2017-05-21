'use strict';

angular.module('sparta')
    .controller('MatchController', function ($stateParams, MatchService) {
        var vm = this;
        vm.matchId = $stateParams.matchId;
        console.log("Match ID",vm.matchId);
        return vm;
    });