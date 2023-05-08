import  { animate, query, style, transition, trigger } from '@angular/animations';

const DEFAULT_ROUTER_ANIMATION_TIMELAPSE = '.25s ease-out';
const DEFAULT_ANIMATION_TIMELAPSE = '.3s ease-out';

export const fader = [

    trigger("translateLeftFader", [

        transition("* <=> *", [

            query(":enter", 
                style({ opacity: 0, transform: 'translateX(-50px)' }),
                { optional: true }
            ),

            query(":leave", 
                style({ display: "none" }),
                { optional: true }
            ),

            query(":enter", 
                animate(DEFAULT_ROUTER_ANIMATION_TIMELAPSE, 
                    style({ opacity: 1, transform: 'none' })
                ),
                { optional: true }
            )

        ])

    ]),

    trigger("elementsFader", [

        transition("* => *", [

            query(":enter", 
                style({ opacity: 0 }),
                { optional: true }
            ),

            query(":leave", 
                style({ display: "none" }),
                { optional: true }
            ),

            query(":enter", 
                animate(DEFAULT_ANIMATION_TIMELAPSE, 
                    style({ opacity: 1 })
                ),
                { optional: true }
            )

        ])

    ])

];

export const spinnerAnimation = [

  trigger('spinnerAnimation', [

    transition(':enter', [
        style({ opacity: 0 }), 
        animate(DEFAULT_ROUTER_ANIMATION_TIMELAPSE, 
            style({ opacity: 1 })
        )
    ]),

    transition(':leave', [
        style({ opacity: 1 }), 
        animate(DEFAULT_ROUTER_ANIMATION_TIMELAPSE, 
            style({ opacity: 0 })
        )
    ]),

  ]),

];