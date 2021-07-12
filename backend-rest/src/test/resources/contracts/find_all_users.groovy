import org.springframework.cloud.contract.spec.Contract

Contract.make {

    request {
        url '/api/v1/user'
        method 'GET'
    }

    response {
        status OK()
        headers {
            contentType applicationJson()
        }
        body (
                users: [
                        [
                            firstName: "Abdel1",
                            lastName: "Boudeffar1"
                        ],
                        [
                            firstName: "Abdel2",
                            lastName: "Boudeffar2"
                        ],
                        [
                            firstName: "Abdel3",
                            lastName: "Boudeffar3"
                        ]
                ]
        )
    }
}