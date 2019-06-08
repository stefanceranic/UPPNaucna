naucnaCentrala.controller('odabirRecenzenata', ['$rootScope','$scope','$state','$http', function ($rootScope, $scope,$state,$http) {

	$scope.prikaziRadoveZaRecenziju = function() {
		$http.get(
				"http://localhost:8080/process/odabirRecenzenata/"
						+ $rootScope.korisnik.email).then(
				function(response) {
					$scope.taskovi = response.data;

				}, function(response) {
				});
	};
	
	$scope.odaberiRad= function(x) {
		$state.go("odaberiRecenzente", {
			"rad" : x.rad,
			"task" : x.taskId,
			"processInstanceId" : x.processId
		});
	};
	
	$scope.prikaziRadoveZaRecenziju();
	
	
}]);