'use strict';

angular.module('sparta')
    .controller('MatchController', function ($stateParams) {
        var vm = this;
        vm.matchId = $stateParams.matchId;
        console.log("Match ID",vm.matchId);
        return vm;
    });