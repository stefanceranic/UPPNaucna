naucnaCentrala.controller('odaberiRecenzente', [ '$rootScope', '$scope', '$state', '$http',
		'$stateParams', function($rootScope, $scope, $state, $http, $stateParams) {

			var rad = $stateParams.rad;
			var task = $stateParams.task;
			var processInstanceId = $stateParams.processInstanceId;
			$scope.odabrani = []; 
			
			$scope.prikaziCasopise = function() {
				$http.get("http://localhost:8080/recenzent/sviRecenzenti/"+task).then(
						function(response) {
							$scope.recenzenti = response.data;

						}, function(response) {
						});
			};
			
			$scope.dodajRecenzenta = function(x) {
				
				if($scope.odabrani.length<2){
					$scope.odabrani.push(x.email);
					var index = $scope.recenzenti.indexOf(x);
					$scope.recenzenti.splice(index, 1);
				}
			};
			
			$scope.potvrdiRecenzente = function() {
				$http.post("http://localhost:8080/recenzent/odabraniRecenzenti/"+task,$scope.odabrani).then(
						function(response) {
							$state.go("pocetnaUrednik")
						}, function(response) {
						});
			};
			$scope.prikaziCasopise();
		} ]);