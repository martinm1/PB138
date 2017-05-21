'use strict';

angular.module('sparta')
    .controller('MatchController', function ($stateParams,MatchResource) {
        var vm = this;
        vm.matchId = $stateParams.matchId;
        console.log("Match ID",vm.matchId);
        return vm;
    });