'use strict';

angular.module('sparta')
    .controller('HomeController', function (MatchService) {
        var vm = this;
        vm.matches = [];
        vm.totalSpectators = 0;
        vm.totalShots = 0;
        vm.selectedTeam = '';

        vm.listOfTeams = ['PSG Zlín','Bílí Tygři Liberec','BK Mladá Boleslav','HC Dynamo Pardubice','HC Energie Karlovy Vary','HC Kometa Brno','HC Oceláři Třinec','HC Olomouc','HC Škoda Plzeň','HC VERVA Litvínov','HC VÍTKOVICE RIDERA','Mountfield Hradec Králové','Piráti Chomutov'];

        vm.selectTeam = function(){
            console.log(vm.selectedTeam);
        };
        vm.refreshAll = function(){
            MatchService.getAllMatches().then(function (data){
                console.log("All matches",data);
                vm.matches = data;
                vm.selectedTeam="";
            });
        };
        vm.refreshHome = function(){
            MatchService.getAllHomeMatches().then(function (data){
                console.log("Home matches",data);
                vm.matches = data;
                vm.selectedTeam="";
            });
        };
        vm.refreshAway = function(){
            MatchService.getAllAwayMatches().then(function (data){
                console.log("Away matches",data);
                vm.matches = data;
                vm.selectedTeam="";
            });
        };
        vm.allAgainstTeam = function(){
            MatchService.getAllMatchesAgainstTeam(vm.selectedTeam).then(function (data){
                console.log("matches",data);
                vm.matches = data;
            });
        };
        function init() {
            MatchService.getAllMatches().then(function (data) {
                console.log("All matches",data);
                vm.matches = data;
            });
            MatchService.countTotalSpectators().then(function (data){
                console.log("Total Spectators",data);
                vm.totalSpectators = data;
            });
            MatchService.countTotalShots().then(function (data){
                console.log("Total Shots",data);
                vm.totalShots = data;
            });
        }

        init();
        return vm;
    });