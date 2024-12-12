function findUniqueDigitPhones() {
    db.phones.aggregate([
        { $project: { display: 1, components: 1, strNumber: {$toString: "$components.number"} } },
        { $match: {
                $expr: {
                    $eq: [
                        {$strLenCP: "$strNumber"},
                        {$size: {$setUnion: {$map: {
                                                input: {$range: [0, {$strLenCP: "$strNumber"}]},
                                                as: "index",
                                                in: {$substrCP: ["$strNumber", "$$index", 1]}
                                            }}
                                }
                        }
                    ]
                }
            }
        }
    ]).forEach(doc => print("Unique digit number:", doc.display));
}