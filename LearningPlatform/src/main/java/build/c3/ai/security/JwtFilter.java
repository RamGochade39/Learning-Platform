package build.c3.ai.security;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import org.springframework.security.authentication.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import build.c3.ai.jwtutil.JwtUtil;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

	private final JwtUtil jwtUtil;
	private final CustomUserDetailsService userDetailsService;

	public JwtFilter(JwtUtil jwtUtil, CustomUserDetailsService userDetailsService) {
		this.jwtUtil = jwtUtil;
		this.userDetailsService = userDetailsService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request,
	                                HttpServletResponse response,
	                                FilterChain chain)
	        throws ServletException, IOException {

	    String header = request.getHeader("Authorization");

	    if (header != null && header.startsWith("Bearer ")) {

	        String token = header.substring(7).trim(); // 🔴 FIX
	        String username = jwtUtil.extractUsername(token);

	        if (username != null &&
	            SecurityContextHolder.getContext().getAuthentication() == null) {

	            var userDetails = userDetailsService.loadUserByUsername(username);

	            UsernamePasswordAuthenticationToken auth =
	                    new UsernamePasswordAuthenticationToken(
	                            userDetails,
	                            null,
	                            userDetails.getAuthorities()
	                    );

	            auth.setDetails(
	                    new WebAuthenticationDetailsSource().buildDetails(request)
	            );

	            SecurityContextHolder.getContext().setAuthentication(auth);
	        }
	    }

	    chain.doFilter(request, response);
	}

}
