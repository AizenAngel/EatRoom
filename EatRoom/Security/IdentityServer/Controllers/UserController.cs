using AutoMapper;
using IdentityServer.DTOs;
using IdentityServer.Entities;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Identity;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace IdentityServer.Controllers
{
    [Route("api/v1/[controller]")]
    [ApiController]
    public class UserController: ControllerBase
    {
        private readonly UserManager<User> _userManager;
        private readonly RoleManager<IdentityRole> _roleManager;
        private readonly IMapper _mapper;

        public UserController(UserManager<User> userManager, RoleManager<IdentityRole> roleManager, IMapper mapper)
        {
            _userManager = userManager ?? throw new ArgumentNullException(nameof(userManager));
            _roleManager = roleManager ?? throw new ArgumentNullException(nameof(roleManager));
            _mapper = mapper ?? throw new ArgumentNullException(nameof(mapper));
        }

        [Authorize(Roles = "Administrator")]
        [HttpGet]
        [ProducesResponseType(typeof(IEnumerable<UserDetails>), StatusCodes.Status200OK)]
        public async Task<ActionResult<IEnumerable<UserDetails>>> GetAllUsers()
        {
            var users = await _userManager.Users.ToListAsync();
            return Ok(_mapper.Map<IEnumerable<UserDetails>>(users));
        }

        [HttpGet("{username}")]
        [ProducesResponseType(typeof(UserDetails), StatusCodes.Status200OK)]
        public async Task<ActionResult<UserDetails>> GetUser(string username)
        {
            var users = await _userManager.Users.FirstOrDefaultAsync(user => user.UserName == username);
            return Ok(_mapper.Map<UserDetails>(users));
        }

        [HttpGet("user/{id}")]
        [ProducesResponseType(typeof(UserRoleDetails), StatusCodes.Status200OK)]
        [ProducesResponseType(typeof(Nullable), StatusCodes.Status400BadRequest)]
        public async Task<ActionResult<UserRoleDetails>> GetUserById(string id)
        {
            try
            {
                var user = await _userManager.Users.FirstOrDefaultAsync(user => user.Id == id);
                var roles = await _userManager.GetRolesAsync(user);
                return Ok(new UserRoleDetails { Id = user.Id, FirstName = user.FirstName, LastName = user.LastName, Email = user.Email, Role = roles[0] });
            } catch(Exception err)
            {
                return BadRequest();
            }

        }

    }
}
