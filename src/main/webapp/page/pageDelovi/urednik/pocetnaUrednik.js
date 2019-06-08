naucnaCentrala.controller('pocetnaUrednik', ['$rootScope', '$scope','$state','$http', function ($rootScope, $scope,$state,$http) {

    $scope.toPrijaviRad = function() {
        $state.go("radoviZaOdobravanje");
    };

    $scope.toRadoviZaIspravku= function() {
        $state.go("odabirRecenzenata");
    };
    $scope.toPregledRecenzija= function() {
    	$state.go("urednikOdabirRadaZaRecenziranje");
    }
    
    $scope.logout = function() {
    	$rootScope.korisnik = "";
    	$state.go("login"); 	
    }
}]);